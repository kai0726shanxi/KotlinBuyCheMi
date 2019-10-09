package com.chmichat.chat.ui.fragment.home

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ImageView
import com.bigkoo.convenientbanner.ConvenientBanner
import com.bigkoo.convenientbanner.holder.Holder
import com.bumptech.glide.Glide
import com.chmichat.chat.App
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseFragment
import com.chmichat.chat.bean.ForumListEntity
import com.chmichat.chat.bean.PostListEntity
import com.chmichat.chat.mvp.contract.home.HomeForumContract
import com.chmichat.chat.mvp.presenter.home.HomeForumPresenter
import com.chmichat.chat.showToast
import com.chmichat.chat.ui.adapter.homeadapter.HomeClassifyAdapter
import com.chmichat.chat.ui.adapter.homeadapter.HomeDynamicAdapter
import com.chmichat.chat.ui.adapter.homeadapter.HomeImageTextAdapter
import com.chmichat.chat.ui.adapter.homeadapter.HomePopularAdapter
import kotlinx.android.synthetic.main.fragment_home_bbs.*

/**
 * @Author 20342
 * @Date 2019/8/7 10:35
 */
class HomeBBSFragment : BaseFragment(), HomeForumContract.View {



    private val mPresenter: HomeForumPresenter by lazy { HomeForumPresenter() }
    private var mHomeClassifyAdapter: HomeClassifyAdapter? = null
    private var mImageTextAdapter: HomeImageTextAdapter? = null
    private var mHomePopularAdapter: HomePopularAdapter? = null
    private var mHomeDynamicAdapter: HomeDynamicAdapter? = null
    private lateinit var mBanner: ConvenientBanner<String>
    private val mlist = arrayListOf("1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1")
    private var mTextTypeface: Typeface? = null
    private var mforumlist = ArrayList<ForumListEntity>()//顶部帖子
    private var mdynamiclist=ArrayList<PostListEntity>()//论坛动态
    private var map = HashMap<String, String>()
    private var hotmap = HashMap<String, String>()
    private var imgmap=HashMap<String,String>()
    private var dynamicmap=HashMap<String,String>()

    companion object {
        fun getInstance(): HomeBBSFragment {
            val fragment = HomeBBSFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_home_bbs

    private var page: Int=1
    private var mTotalPage: Int?=0

    override fun initView() {
        mPresenter.attachView(this)
        // Adapter
        //顶部论坛
        mHomeClassifyAdapter = activity?.let { HomeClassifyAdapter(it, mforumlist) }
        //综合图文
        mImageTextAdapter = activity?.let { HomeImageTextAdapter(it, ArrayList()) }
        //热门帖子
        mHomePopularAdapter = activity?.let { HomePopularAdapter(it, ArrayList()) }
        //论坛动态
        mHomeDynamicAdapter = activity?.let { HomeDynamicAdapter(it, mdynamiclist) }
        mTextTypeface?.let { mHomeClassifyAdapter?.setTextStyle(it) }

        recycle_popular.isNestedScrollingEnabled = false
        recycle_bbs.isNestedScrollingEnabled = false

        recycle_view.adapter = mHomeClassifyAdapter
        recycle_view.layoutManager = linearLayoutManager
        recycle_view.itemAnimator = DefaultItemAnimator()
        recycle_imageandtext.adapter = mImageTextAdapter
        recycle_imageandtext.layoutManager = imlinearLayoutManager
        recycle_popular.adapter = mHomePopularAdapter
        recycle_popular.layoutManager = plinearLayoutManager
        recycle_bbs.adapter = mHomeDynamicAdapter
        recycle_bbs.layoutManager = LinearLayoutManager(activity)

        mBanner = home_banner as ConvenientBanner<String>
        mBanner.setPages({ NetworkImageHolderView() }, mlist)
                .startTurning(2000)


        refreshLayout.setOnRefreshListener { refreshLayout ->
            //下拉刷新
            page=1
            setpushdata()
            refreshLayout.finishRefresh()
        }
        refreshLayout.setOnLoadMoreListener { refreshLayout ->
            //加载更多
            page++
            if (page<mTotalPage!!){
                setpushdynamicdata()
                refreshLayout.finishLoadMore()

            }else{
                refreshLayout.finishLoadMore(1000, true, true)

            }

        }
    }

    private fun setpushdata() {
        mPresenter.getForumList(map)
        hotmap.clear()
        imgmap.clear()
        dynamicmap.clear()
        hotmap["isHot"] = "1"
        hotmap["types"]="1,2"
        mPresenter.getHotPostList(hotmap)
        imgmap["type"]="1"
        mPresenter.getImagePostList(imgmap)
        dynamicmap["sortField"]="3"
        hotmap["types"]="1,2"
        dynamicmap["pageSize"]="10"
        dynamicmap["pageNum"]=page.toString()
        mPresenter.getOnDynamicPostList(dynamicmap)
    }


    private fun setpushdynamicdata(){
        dynamicmap.clear()
        dynamicmap["sortField"]="3"
        hotmap["types"]="1,2"
        dynamicmap["pageSize"]="10"
        dynamicmap["pageNum"]=page.toString()
        mPresenter.getOnDynamicPostList(dynamicmap)
    }

    init {
        mTextTypeface = Typeface.createFromAsset(App.context.assets, "fonts/FZjianti.ttf")

    }

    override fun lazyLoad() {
        mPresenter.getForumList(map)

        hotmap.clear()
        imgmap.clear()
        dynamicmap.clear()
        hotmap["isHot"] = "1"
        hotmap["type"]="2"

        mPresenter.getHotPostList(hotmap)
        imgmap["type"]="1"
        mPresenter.getImagePostList(imgmap)
        dynamicmap["sortField"]="3"
        mPresenter.getOnDynamicPostList(dynamicmap)


    }

    private val linearLayoutManager by lazy {
        LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

    }
    private val imlinearLayoutManager by lazy {
        LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

    }
    private val plinearLayoutManager by lazy {
        LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

    }

    class NetworkImageHolderView : Holder<String> {
        private var imageView: ImageView? = null

        override fun createView(context: Context): View {
            //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页

            imageView = ImageView(context)
            imageView!!.scaleType = ImageView.ScaleType.FIT_XY
            return imageView as ImageView
        }

        override fun UpdateUI(context: Context, position: Int, data: String) {
            Glide.with(context).load("https://img-blh.buychemi.com/20190321/2217102976504300546.png")
                    .into(imageView!!)


        }
    }

    override fun OnforumList(data: ArrayList<ForumListEntity>?) {
        if (data != null && data.size > 0) {
            mHomeClassifyAdapter?.addItemData(data!!)

        }
    }

    override fun showError(emg: String, code: Int) {
       ShowErrorMes(emg,code)
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    override fun onHotPostList(data: ArrayList<PostListEntity>?) {
//hot post
        if (data != null) {
            mHomePopularAdapter?.addData(data)
        }
    }

    override fun OnImagePostList(data: ArrayList<PostListEntity>?) {
//综合图文
        if (data != null) {
            mImageTextAdapter?.addDataNew(data)
        }
    }


    override fun OnDynamicPostList(data: ArrayList<PostListEntity>?,pagetotal:Int?) {
        mTotalPage=pagetotal
        if (data!=null){
            if (page==1){
                mHomeDynamicAdapter?.addDataNew(data)

            }else{
                mHomeDynamicAdapter?.addDataAll(data)

            }
        }

    }

}
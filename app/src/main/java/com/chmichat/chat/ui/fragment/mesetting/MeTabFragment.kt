package com.chmichat.chat.ui.fragment.mesetting

import android.graphics.Rect
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseFragment
import com.chmichat.chat.bean.PostListEntity
import com.chmichat.chat.mvp.contract.me.MeTabContract
import com.chmichat.chat.mvp.presenter.me.MeTabPresenter
import com.chmichat.chat.net.exception.ErrorStatus
import com.chmichat.chat.showToast
import com.chmichat.chat.ui.adapter.me.MeDynamicAdapter
import com.chmichat.chat.utils.DisplayManager
import kotlinx.android.synthetic.main.fragment_metab_layout.*

/**
 * 我的界面底部分类
 * @Author 20342
 * @Date 2019/8/9 10:21
 */
class MeTabFragment : BaseFragment(), MeTabContract.View {


    private var mlist = ArrayList<PostListEntity>()
    private val mPresenter by lazy { MeTabPresenter() }
    private var mTotalPage:Int?=0

    private var mTitle: String? = null
    private var mMeDynamicAdapter: MeDynamicAdapter? = null
    private var checkPosition: Int = 0
    private var map = HashMap<String, String>()
    private var mlun: MeCollectFragment? = null
    private var mtie: MeCollectFragment? = null
    private var mvideo: MeCollectFragment? = null
    private var mlongvideo: MeCollectFragment? = null
    private var ismedata: String? = ""
    private var page:Int=1

    companion object {
        fun getInstance(title: String): MeTabFragment {
            val fragment = MeTabFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }

        fun getInstance(title: String, isme: String?): MeTabFragment {
            val fragment = MeTabFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            fragment.ismedata = isme
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_metab_layout

    override fun initView() {
        mPresenter.attachView(this)
        mLayoutStatusView = multipleStatusView

        if (!mTitle.isNullOrEmpty()) {
            ShowView(mTitle!!)

        }

        switchFragment(0)
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
                setpushdata()
                refreshLayout.finishLoadMore()

            }else{
                refreshLayout.finishLoadMore(1000, true, true)

            }

        }


    }

    override fun lazyLoad() {
        if (!mTitle.isNullOrEmpty()) {

            setpushdata()


        }

    }

    private fun setpushdata() {
        if (ismedata.isNullOrEmpty()) {
            when (mTitle) {
                "0" -> {
                    map.clear()
                    map["types"] = "1,2"
                }
                "1" -> {
                    map.clear()
                    map["type"] = "3"
                }
                "2" -> {
                    map.clear()
                    map["type"] = "4"
                }
            }
            map["pageSize"]="10"
            map["pageNum"]=page.toString()
            mPresenter.getMyPostList(map)

        } else {
            when (mTitle) {
                "0" -> {
                    map.clear()
                    map["types"] = "1,2"
                }
                "1" -> {
                    map.clear()
                    map["type"] = "3"

                }
                "2" -> {
                    map.clear()
                    map["type"] = "4"

                }
            }
            map["pageSize"]="10"
            map["pageNum"]=page.toString()
            mPresenter.getPostList(map)

        }


    }

    private fun ShowView(s: String) {
        when {
            s == "0" -> {
                rg.visibility = View.GONE
                fl_content.visibility = View.GONE
                refreshLayout.visibility = View.VISIBLE
                mMeDynamicAdapter = activity?.let { MeDynamicAdapter(it, mlist) }
                mRecyclerView.adapter = mMeDynamicAdapter
                mRecyclerView.layoutManager = LinearLayoutManager(activity)

            }
            s == "1" -> {
                rg.visibility = View.GONE
                fl_content.visibility = View.GONE
                refreshLayout.visibility = View.VISIBLE

                mMeDynamicAdapter = activity?.let { MeDynamicAdapter(it, mlist) }
                mRecyclerView.adapter = mMeDynamicAdapter
                mRecyclerView.layoutManager = GridLayoutManager(activity, 2)
                mRecyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
                    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
                        val position = parent.getChildPosition(view)
                        val offset = DisplayManager.dip2px(2f)!!

                        outRect.set(if (position % 2 == 0) 20 else offset, offset,
                                if (position % 2 == 0) offset else 20, offset)
                    }

                })
            }
            s == "2" -> {
                rg.visibility = View.GONE
                fl_content.visibility = View.GONE
                refreshLayout.visibility = View.VISIBLE

                mMeDynamicAdapter = activity?.let { MeDynamicAdapter(it, mlist) }
                mRecyclerView.adapter = mMeDynamicAdapter
                mRecyclerView.layoutManager = LinearLayoutManager(activity)

            }
            s == "3" -> {
                //收藏类型1版块（默认）2帖子，3小视频，4VLOG

                rg.visibility = View.VISIBLE
                fl_content.visibility = View.VISIBLE
                refreshLayout.visibility = View.GONE
              //  mPresenter.getCollectList("1")
                rg.setOnCheckedChangeListener { _, checkedId ->
                    when (checkedId) {

                        R.id.rbimg -> {
                            checkPosition = 0


                            switchFragment(0)
                        }
                        R.id.rbtie -> {
                            checkPosition = 1

                            switchFragment(1)

                        }
                        R.id.rbvideo -> {
                            checkPosition = 2

                            switchFragment(2)
                        }
                        R.id.rblongvideo -> {
                            checkPosition = 3

                            switchFragment(3)

                        }


                    }


                }
            }


        }

    }

    override fun setCollectList(data: ArrayList<PostListEntity>?, total: Int) {

    }

    override fun showError(errorMsg: String, errorCode: Int) {
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            mLayoutStatusView?.showNoNetwork()
        } else {
            mLayoutStatusView?.showError()
        }
        ShowErrorMes(errorMsg,errorCode)
    }

    override fun showLoading() {

        mLayoutStatusView?.showLoading()

    }

    override fun dismissLoading() {
        mLayoutStatusView?.showContent()
    }

    override fun onMyPostList(data: ArrayList<PostListEntity>?,totalpage:Int?) {
        mTotalPage=totalpage
        if (data != null) {
           if (page==1){
               mMeDynamicAdapter?.addDataNew(data)

           }else{
               mMeDynamicAdapter?.addDataAll(data)

           }
        }else{
            if (page==1){
                mLayoutStatusView?.showEmpty()
            }
        }

    }

    /**
     * 隐藏所有的Fragment
     * @param transaction transaction
     */
    private fun hideFragments(transaction: FragmentTransaction) {
        mlun?.let { transaction.hide(it) }
        mtie?.let { transaction.hide(it) }
        mvideo?.let { transaction.hide(it) }
        mlongvideo?.let { transaction.hide(it) }
    }


    /**
     * 切换Fragment
     * @param position 下标
     */
    private fun switchFragment(position: Int) {
        val transaction = childFragmentManager.beginTransaction()
        hideFragments(transaction)
        when (position) {
            0 // 论坛
            -> mlun?.let {
                transaction.show(it)
            } ?: MeCollectFragment.getInstance("1").let {
                mlun = it
                transaction.add(R.id.fl_content, it, "lun")
            }
            1  //帖子
            -> mtie?.let {
                transaction.show(it)
            } ?: MeCollectFragment.getInstance("2").let {
                mtie = it
                transaction.add(R.id.fl_content, it, "tie")
            }

            2 //视频
            -> mvideo?.let {
                transaction.show(it)
            } ?: MeCollectFragment.getInstance("3").let {
                mvideo = it
                transaction.add(R.id.fl_content, it, "video")
            }
            3 //长视频
            -> mlongvideo?.let {

                transaction.show(it)
            } ?: MeCollectFragment.getInstance("4").let {
                mlongvideo = it
                transaction.add(R.id.fl_content, it, "lvideo")
            }

            else -> {

            }
        }
        transaction.commitAllowingStateLoss()
    }
}



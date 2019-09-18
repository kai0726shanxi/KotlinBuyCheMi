package com.chmichat.chat.ui.activity.home

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chmichat.chat.Constants
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseActivity
import com.chmichat.chat.bean.ForumListEntity
import com.chmichat.chat.bean.PostListEntity
import com.chmichat.chat.mvp.contract.discover.DiscoverContract
import com.chmichat.chat.mvp.contract.discover.DiscoverTabContract
import com.chmichat.chat.mvp.presenter.discover.DiscoverTabPresenter
import com.chmichat.chat.showToast
import com.chmichat.chat.ui.adapter.homeadapter.ActivityProjectAdapter
import com.chmichat.chat.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_activity_project.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * @Author 20342
 * @Date 2019/8/17 15:47
 */
class ActivityProjectActivity : BaseActivity(), DiscoverTabContract.View, View.OnClickListener {


    private var mAvtivityProjectAdapter: ActivityProjectAdapter? = null
    private var mlist = ArrayList<PostListEntity>()
    private var mPostListEntity: PostListEntity? = null
    private var map = HashMap<String, String>()
    private var page: Int = 1
    private var mTotalPage: Int? = 0
    override fun layoutId(): Int {
        return R.layout.activity_activity_project
    }

    private val mPresenter: DiscoverTabPresenter by lazy { DiscoverTabPresenter() }

    override fun initData() {

        mPostListEntity = intent.getSerializableExtra(Constants.KEYNAME) as PostListEntity
    }


    override fun initView() {
        mPresenter.attachView(this)
        mLayoutStatusView=multipleStatusView
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)
        iv_left.visibility = View.VISIBLE
        tv_title.text = "活动专题"
        iv_left.setColorFilter(Color.BLACK)
        iv_left.setOnClickListener(this)
        mAvtivityProjectAdapter = ActivityProjectAdapter(this, mlist)
        recycle_view.adapter = mAvtivityProjectAdapter
        recycle_view.layoutManager = LayoutManager


        refreshLayout.setOnRefreshListener { refreshLayout ->
            //下拉刷新
            page=1
            setpushData()
            refreshLayout.finishRefresh()
        }
        refreshLayout.setOnLoadMoreListener { refreshLayout ->
            //加载更多
            page++
            if (page<mTotalPage!!){
                setpushData()
                refreshLayout.finishLoadMore()

            }else{
                refreshLayout.finishLoadMore(1000, true, true)

            }

        }
    }

    override fun start() {
        setpushData()
    }

    private fun setpushData() {
        map.clear()
        map["pageSize"] = "10"
        map["pageNum"] = page.toString()
        map["sectionId"] = mPostListEntity?.id.toString()
        map["type"] = "1"
        mPresenter.getPostList(map)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_left -> {
                finish()
            }
        }
    }

    private val LayoutManager by lazy {
        LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }


    override fun onPostList(data: ArrayList<PostListEntity>?, pagetotal: Int?) {
        mTotalPage=pagetotal
        if (data!=null){
            if (page==1){
               mAvtivityProjectAdapter?.addDataNew(data)
            }else{
                mAvtivityProjectAdapter?.addDataAll(data)

            }


        }else{
            if (page==1){
                mLayoutStatusView?.showEmpty()
            }

        }
    }

    override fun showError(errorMsg: String, errorCode: Int) {
        showToast(errorMsg)
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }
}
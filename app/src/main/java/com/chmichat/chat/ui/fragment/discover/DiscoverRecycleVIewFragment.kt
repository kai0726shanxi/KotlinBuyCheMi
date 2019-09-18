package com.chmichat.chat.ui.fragment.discover

import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseFragment
import com.chmichat.chat.bean.PostListEntity
import com.chmichat.chat.mvp.contract.discover.DiscoverTabContract
import com.chmichat.chat.mvp.presenter.discover.DiscoverTabPresenter
import com.chmichat.chat.net.exception.ErrorStatus
import com.chmichat.chat.showToast
import com.chmichat.chat.ui.adapter.discover.DiscoverContentAdapter
import com.chmichat.chat.utils.DisplayManager
import kotlinx.android.synthetic.main.fragment_discover_recycle.*

/**
 * 发现界面下面的分页
 * @Author 20342
 * @Date 2019/8/22 14:34
 */
class DiscoverRecycleVIewFragment : BaseFragment(), DiscoverTabContract.View {


    private val mPresenter: DiscoverTabPresenter by lazy { DiscoverTabPresenter() }
    private var mPosition = "0"
    private var mId: Int? = 0
    private var mlist = ArrayList<PostListEntity>()
    private var mDiscoverContentAdapter: DiscoverContentAdapter? = null
    private var map = HashMap<String, String>()
    private var page: Int = 1
    private var mTotalPage: Int? = 0
    private var mType: String? = ""

    companion object {
        fun getInstance(string: String): DiscoverRecycleVIewFragment {
            val fragment = DiscoverRecycleVIewFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mPosition = string
            return fragment
        }

        fun getInstance(string: String, forumid: Int): DiscoverRecycleVIewFragment {
            val fragment = DiscoverRecycleVIewFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mPosition = string
            fragment.mId = forumid
            return fragment
        }

        fun getInstance(string: String, forumid: Int?, type: String): DiscoverRecycleVIewFragment {
            val fragment = DiscoverRecycleVIewFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mPosition = string
            fragment.mId = forumid
            fragment.mType = type
            return fragment
        }


    }

    override fun getLayoutId(): Int {

        return R.layout.fragment_discover_recycle
    }


    override fun initView() {
        mPresenter.attachView(this)
        mDiscoverContentAdapter = activity?.let { DiscoverContentAdapter(it, mlist, mPosition) }
        mLayoutStatusView = multipleStatusView
        if ( mType == "bbs") {
            recycle_view.adapter = mDiscoverContentAdapter
            recycle_view.layoutManager = LinearLayoutManager(activity)
        } else {
            if (mPosition != "1") {
                recycle_view.adapter = mDiscoverContentAdapter
                recycle_view.layoutManager = LinearLayoutManager(activity)
            } else {
                recycle_view.adapter = mDiscoverContentAdapter
                recycle_view.layoutManager = GridLayoutManager(activity, 2)
                recycle_view.addItemDecoration(object : RecyclerView.ItemDecoration() {
                    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
                        val position = parent.getChildPosition(view)
                        val offset = DisplayManager.dip2px(2f)!!

                        outRect.set(if (position % 2 == 0) 20 else offset, offset,
                                if (position % 2 == 0) offset else 20, offset)
                    }

                })
            }
        }



        refreshLayout.setOnRefreshListener { refreshLayout ->
            //下拉刷新
            page = 1
            setpushdata()
            refreshLayout.finishRefresh()
        }
        refreshLayout.setOnLoadMoreListener { refreshLayout ->
            //加载更多
            page++
            if (page < mTotalPage!!) {
                setpushdata()
                refreshLayout.finishLoadMore()

            } else {
                refreshLayout.finishLoadMore(1000, true, true)

            }

        }

    }

    private fun setpushdata() {
        if (mType != "bbs") {
            when (mPosition) {

                "0" -> {
                    map.clear()
                    map["sectionId"] = mId.toString()
                    map["types"] = "1,2"
                }
                "1" -> {
                    map.clear()
                    map["sectionId"] = mId.toString()
                    map["type"] = "3"
                }

                "2" -> {
                    map.clear()
                    map["sectionId"] = mId.toString()
                    map["type"] = "4"

                }


            }
            map["pageSize"] = "10"
            map["pageNum"] = page.toString()
            mPresenter.getPostList(map)


        } else {
            when (mPosition) {
               //排序字段（1全部2精华3最新）
                "0" -> {
                    map.clear()
                    map["sectionId"] = mId.toString()
                    map["sortField"] = "1"
                    map["types"] = "1,2"
                }
                "1" -> {
                    map.clear()
                    map["sectionId"] = mId.toString()
                    map["types"] = "1,2"
                    map["sortField"] = "2"
                    map["isEssence"]="1"

                }

                "2" -> {
                    map.clear()
                    map["sectionId"] = mId.toString()
                    map["types"] = "1,2"
                    map["sortField"] = "3"

                }


            }
            map["pageSize"] = "10"
            map["pageNum"] = page.toString()
            mPresenter.getPostList(map)


        }
    }

    override fun lazyLoad() {


    }

    override fun onPostList(data: ArrayList<PostListEntity>?, pagetotal: Int?) {
        mTotalPage = pagetotal
        if (data != null) {
            if (!mPosition.isNullOrEmpty() && mPosition != "5") {
                if (page == 1) {
                    mDiscoverContentAdapter?.addDataNew(data)

                } else {
                    mDiscoverContentAdapter?.addDataAll(data)

                }
            } else {

            }
        } else {
            if (page == 1) {
                mLayoutStatusView?.showEmpty()
            }
        }
    }


    override fun showError(errorMsg: String, errorCode: Int) {
        showToast(errorMsg)
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            mLayoutStatusView?.showNoNetwork()
        } else {
            mLayoutStatusView?.showError()
        }
    }

    override fun showLoading() {

        mLayoutStatusView?.showLoading()

    }

    override fun dismissLoading() {
        mLayoutStatusView?.showContent()
    }


    override fun onStart() {
        super.onStart()
        page=1
        setpushdata()

    }
}
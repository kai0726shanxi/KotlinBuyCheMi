package com.chmichat.chat.ui.fragment.mesetting

import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseFragment
import com.chmichat.chat.bean.PostListEntity
import com.chmichat.chat.mvp.contract.me.MeCollectContract
import com.chmichat.chat.mvp.presenter.me.MeCollectPresenter
import com.chmichat.chat.showToast
import com.chmichat.chat.ui.adapter.me.MyCollectAdapter
import com.chmichat.chat.utils.DisplayManager
import kotlinx.android.synthetic.main.fragment_collect_layout.*

/**
 * @Author 20342
 * @Date 2019/9/12 16:56
 */
class MeCollectFragment : BaseFragment(), MeCollectContract.View {

    private val mPresenter: MeCollectPresenter by lazy { MeCollectPresenter() }
    private var mtype: String? = ""
    private var mMeCollectAdapter: MyCollectAdapter? = null
    private var mlist = ArrayList<PostListEntity>()
    private var page: Int = 1
    private var mTotalPage: Int? = 0
    private var map = HashMap<String, String>()

    companion object {
        fun getInstance(type: String): MeCollectFragment {
            val fragment = MeCollectFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mtype = type
            return fragment

        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_collect_layout
    }

    override fun initView() {
        mPresenter.attachView(this)
        mLayoutStatusView = multipleStatusView
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

        if (!mtype.isNullOrEmpty() && mtype == "3") {
            mMeCollectAdapter = activity?.let { MyCollectAdapter(it, mlist) }
            mRecyclerView.adapter = mMeCollectAdapter
            mRecyclerView.layoutManager = GridLayoutManager(activity, 2)
            mRecyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
                    val position = parent.getChildPosition(view)
                    val offset = DisplayManager.dip2px(2f)!!

                    outRect.set(if (position % 2 == 0) 20 else offset, offset,
                            if (position % 2 == 0) offset else 20, offset)
                }

            })
        } else {
            mMeCollectAdapter = activity?.let { MyCollectAdapter(it, mlist) }
            mRecyclerView.adapter = mMeCollectAdapter
            mRecyclerView.layoutManager = LinearLayoutManager(activity)
        }

    }

    private fun setpushdata() {
        if (!mtype.isNullOrEmpty()) {

            map.clear()
            map["pageSize"] = "10"
            map["pageNum"] = page.toString()
            when (mtype) {
                "1" -> {
                    map["collectionType"] = "1"
                }
                "2" -> {
                    map["collectionType"] = "2"


                }
                "3" -> {
                    map["collectionType"] = "3"


                }
                "4" -> {
                    map["collectionType"] = "4"


                }

            }
            mPresenter.getCollectList(map)

        }
    }

    override fun lazyLoad() {
        setpushdata()
    }

    override fun setCollectList(data: ArrayList<PostListEntity>?, totalsize: Int) {

        //列表数据
        if (data != null) {
            if (page == 1) {
                mlist.clear()
                mlist.addAll(data)
                mMeCollectAdapter?.addDataNew(data)

            } else {
                mlist.addAll(data)
                mMeCollectAdapter?.addDataAll(data)

            }

        } else {
            if (page == 1) {
                mLayoutStatusView?.showEmpty()

            }
        }


    }

    override fun showError(errorMsg: String, errorCode: Int) {
        ShowErrorMes(errorMsg,errorCode)
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    override fun onStart() {
        super.onStart()
       /* if (mlist == null || mlist.size == 0) {
            mLayoutStatusView?.showEmpty()
        }*/
    }
}
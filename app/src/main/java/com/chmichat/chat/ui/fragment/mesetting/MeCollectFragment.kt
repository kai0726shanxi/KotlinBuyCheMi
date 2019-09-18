package com.chmichat.chat.ui.fragment.mesetting

import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseFragment
import com.chmichat.chat.bean.CollectEntity
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

    private val mPresenter:MeCollectPresenter by lazy { MeCollectPresenter() }
    private var mtype: String? = ""
    private var mMeCollectAdapter: MyCollectAdapter? = null
    private var mlist = ArrayList<CollectEntity>()

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

        if (!mtype.isNullOrEmpty()&&mtype=="3"){
            mMeCollectAdapter = activity?.let { MyCollectAdapter(it, mlist) }
            mRecyclerView.adapter = mMeCollectAdapter
            mRecyclerView.layoutManager = GridLayoutManager(activity, 2)
            mRecyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
                    val position = parent.getChildPosition(view)
                    val offset = DisplayManager.dip2px(5f)!!

                    outRect.set(if (position % 2 == 0) 10 else offset, offset,
                            if (position % 2 == 0) offset else 10, offset)
                }

            })
        }else{
            mMeCollectAdapter = activity?.let { MyCollectAdapter(it, mlist) }
            mRecyclerView.adapter = mMeCollectAdapter
            mRecyclerView.layoutManager = LinearLayoutManager(activity)
        }

    }

    override fun lazyLoad() {
        if (!mtype.isNullOrEmpty()){
            when(mtype){
                 "1"->{
                    mPresenter.getCollectList("1")
                }
                "2"->{
                    mPresenter.getCollectList("2")

                }
                "3"->{
                    mPresenter.getCollectList("3")

                }
                "4"->{
                    mPresenter.getCollectList("4")

                }

            }
        }
    }

    override fun setCollectList(data: ArrayList<CollectEntity>?, totalsize: Int) {

        //列表数据
        if (data == null) {
            mLayoutStatusView?.showEmpty()
            return
        }

        mMeCollectAdapter?.addDataNew(data)

    }

    override fun showError(errorMsg: String, errorCode: Int) {
        showToast(errorMsg)
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }
}
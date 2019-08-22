package com.chmichat.chat.ui.fragment.discover

import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseFragment
import com.chmichat.chat.ui.adapter.discover.DiscoverContentAdapter
import com.chmichat.chat.ui.adapter.homeadapter.AllDynamicAdapter
import com.chmichat.chat.utils.DisplayManager
import kotlinx.android.synthetic.main.fragment_discover_recycle.*

/**
 * @Author 20342
 * @Date 2019/8/22 14:34
 */
class DiscoverRecycleVIewFragment : BaseFragment() {
    private var mPosition = "0"
    private var mlist= arrayListOf("1","1","1","1","1","1","1","1","1","1","1","1","1")
    private var mDiscoverContentAdapter: DiscoverContentAdapter? = null
    private var mAllDynamicAdapter: AllDynamicAdapter?=null

    companion object {
        fun getInstance(string: String): DiscoverRecycleVIewFragment {
            val fragment = DiscoverRecycleVIewFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mPosition = string
            return fragment
        }
    }

    override fun getLayoutId(): Int {

        return R.layout.fragment_discover_recycle
    }

    override fun initView() {
        mDiscoverContentAdapter=activity?.let { DiscoverContentAdapter(it,mlist,mPosition) }

        if (mPosition!="2"){

            if (mPosition=="5"){
               mAllDynamicAdapter=activity?.let { AllDynamicAdapter(it,mlist)

               }
                recycle_view.adapter=mAllDynamicAdapter
                recycle_view.layoutManager=LinearLayoutManager(activity)
            }else{
                recycle_view.adapter=mDiscoverContentAdapter
                recycle_view.layoutManager=LinearLayoutManager(activity)
            }


        }else{
            recycle_view.adapter=mDiscoverContentAdapter
            recycle_view.layoutManager = GridLayoutManager(activity, 2)
            recycle_view.addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
                    val position = parent.getChildPosition(view)
                    val offset = DisplayManager.dip2px(5f)!!

                    outRect.set(if (position % 2 == 0) 10 else offset, offset,
                            if (position % 2 == 0) offset else 10, offset)
                }

            })
        }

    }

    override fun lazyLoad() {
    }
}
package com.chmichat.chat.ui.fragment.home

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseFragment
import com.chmichat.chat.ui.adapter.homeadapter.HomeIntrudictionAdapter
import kotlinx.android.synthetic.main.fragment_home_introduction.*

/**
 * @Author 20342
 * @Date 2019/8/21 9:18
 */
class HomeIntroductionFragment:BaseFragment() {

   var mlist= arrayListOf("1","3","1","1","1","1","1","1","1","1","1","1","1","1")
   var mHomeIntrudictionAdapter: HomeIntrudictionAdapter?=null
    companion object {
        fun getInstance():HomeIntroductionFragment{
            val fragment=HomeIntroductionFragment()
            val bundle=Bundle()
            fragment.arguments=bundle
            return fragment
        }
    }
    override fun getLayoutId(): Int = R.layout.fragment_home_introduction

    override fun initView() {
        mHomeIntrudictionAdapter=activity?.let { HomeIntrudictionAdapter(it,mlist) }
        mRecyclerView.isNestedScrollingEnabled=false
        mRecyclerView.adapter=mHomeIntrudictionAdapter
        mRecyclerView.layoutManager=LinearLayoutManager(activity)

    }

    override fun lazyLoad() {

    }
}
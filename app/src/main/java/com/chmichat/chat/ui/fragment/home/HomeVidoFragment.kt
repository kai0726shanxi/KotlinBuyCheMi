package com.chmichat.chat.ui.fragment.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseFragment
import com.chmichat.chat.base.BaseFragmentAdapter
import com.chmichat.chat.ui.adapter.homeadapter.HomeVIdeoForumAdapter
import com.chmichat.chat.ui.fragment.mesetting.MeTabFragment
import kotlinx.android.synthetic.main.fragment_home_video.*

/**
 * @Author 20342
 * @Date 2019/8/7 10:36
 */
class HomeVidoFragment : BaseFragment() {

    private val mTitles = arrayListOf("精选", "小视频", "VLOG")
    private var mFragments = arrayListOf<Fragment>(MeTabFragment.getInstance("1"), MeTabFragment.getInstance("1"), MeTabFragment.getInstance("2"))

    companion object {
        fun getInstance(): HomeVidoFragment {
            val fragment = HomeVidoFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_home_video

    override fun initView() {
        mViewPager.adapter = BaseFragmentAdapter(childFragmentManager, mFragments, mTitles)

        tab_layout.setViewPager(mViewPager)

        recycle_view.adapter=activity?.let { HomeVIdeoForumAdapter(it,mTitles) }
        recycle_view.layoutManager=LinearLayoutManager(activity,LinearLayout.HORIZONTAL,false)

    }

    override fun lazyLoad() {
    }
}
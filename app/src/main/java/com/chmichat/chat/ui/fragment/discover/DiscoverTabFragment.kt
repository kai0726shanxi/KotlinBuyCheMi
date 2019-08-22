package com.chmichat.chat.ui.fragment.discover

import android.support.v4.app.Fragment
import android.os.Bundle
import android.widget.FrameLayout
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseFragment
import com.chmichat.chat.base.BaseFragmentAdapter
import kotlinx.android.synthetic.main.fragment_tab_discover.*

/**
 * @Author 20342
 * @Date 2019/8/22 13:50
 */
class DiscoverTabFragment : BaseFragment() {
    private var mPosition = ""
    private var mTitles = arrayListOf("图文", "帖子", "小视频", "VLOG")
    private var mFragmentList = ArrayList<Fragment>()

    companion object {
        fun getInstance(pos: String): DiscoverTabFragment {
            val fragment = DiscoverTabFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mPosition = pos
            return fragment
        }
    }


    override fun getLayoutId(): Int {

        return R.layout.fragment_tab_discover

    }

    override fun initView() {
        mFragmentList.clear()
        tab_layout.isIsshowbg=true
        tab_layout.maxTxtSize=14
        tab_layout.minTxtSize=14
        for (index in mTitles.indices) {

            mFragmentList.add(DiscoverRecycleVIewFragment.getInstance("$index"))
        }
        mViewPager.adapter = BaseFragmentAdapter(childFragmentManager, mFragmentList, mTitles)
        tab_layout.setViewPager(mViewPager)
    }

    override fun lazyLoad() {
    }
}
package com.chmichat.chat.ui.fragment.home

import android.os.Bundle
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseFragment

/**
 * @Author 20342
 * @Date 2019/8/7 10:36
 */
class HomeVidoFragment :BaseFragment() {
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
    }

    override fun lazyLoad() {
    }
}
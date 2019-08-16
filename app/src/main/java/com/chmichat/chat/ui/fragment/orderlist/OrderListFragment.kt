package com.chmichat.chat.ui.fragment.orderlist

import android.os.Bundle
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseFragment
import com.chmichat.chat.utils.StatusBarUtil
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * @Author 20342
 * @Date 2019/8/5 11:41
 */
class OrderListFragment : BaseFragment() {

    companion object {
        fun getInstance(): OrderListFragment {
            val fragment = OrderListFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_order_layout

    override fun initView() {
        //状态栏透明和间距处理
        activity?.let { StatusBarUtil.darkMode(it) }
        activity?.let { StatusBarUtil.setPaddingSmart(it, cl_bar) }
    }

    override fun lazyLoad() {
    }
}
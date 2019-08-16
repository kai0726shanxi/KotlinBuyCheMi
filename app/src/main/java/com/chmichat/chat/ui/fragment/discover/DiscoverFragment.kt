package com.chmichat.chat.ui.fragment.discover

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseFragment
import com.chmichat.chat.showToast
import com.chmichat.chat.ui.adapter.discover.DiscoverContentAdapter
import com.chmichat.chat.ui.adapter.discover.DiscoverTitleAdapter
import com.chmichat.chat.utils.StatusBarUtil
import kotlinx.android.synthetic.main.fragment_discover.*

/**
 * 发现模块
 * @Author 20342
 * @Date 2019/8/5 11:30
 */
class DiscoverFragment : BaseFragment() {
    private val mlist = arrayListOf("推荐", "原料", "进口", "常识", "百科", "行业", "测试1", "测试2", "测试3", "测试4", "测试5")
    private var titleAdapter:DiscoverTitleAdapter?=null
    private var contentAdapter: DiscoverContentAdapter?=null


    companion object {
        fun getInstance(): DiscoverFragment {
            val fragment = DiscoverFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }


    override fun getLayoutId(): Int = R.layout.fragment_discover

    override fun initView() {
        //状态栏透明和间距处理
        activity?.let { StatusBarUtil.darkMode(it) }
        activity?.let { StatusBarUtil.setPaddingSmart(it, cl_bar) }
        titleAdapter=activity?.let { DiscoverTitleAdapter(it, mlist) }
        contentAdapter=activity?.let { DiscoverContentAdapter(it,mlist) }
        recycle_title.adapter=titleAdapter
        recycle_title.layoutManager=linearLayoutManager
        recycle_content.adapter=contentAdapter
        recycle_content.layoutManager=clinearLayoutManager
        titleAdapter!!.setOnTitleItemClickListener { tag, i ->

            showToast(tag)
            titleAdapter!!.mposition=i
            titleAdapter!!.notifyDataSetChanged()
        }

    }

    override fun lazyLoad() {
    }

    private val linearLayoutManager by lazy {
        LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
    }
    private val clinearLayoutManager by lazy {
        LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }
}
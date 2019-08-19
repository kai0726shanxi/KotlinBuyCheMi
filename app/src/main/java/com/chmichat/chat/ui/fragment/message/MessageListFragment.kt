package com.chmichat.chat.ui.fragment.message

import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseFragment
import com.chmichat.chat.ui.adapter.message.MessageListAdapter
import com.chmichat.chat.utils.StatusBarUtil
import kotlinx.android.synthetic.main.fragment_order_layout.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * @Author 20342
 * @Date 2019/8/5 11:41
 */
class MessageListFragment : BaseFragment(),View.OnClickListener {


    private var mMessageListAdapter: MessageListAdapter?=null
    var mlist= arrayListOf("1","2","w","w")
    companion object {
        fun getInstance(): MessageListFragment {
            val fragment = MessageListFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_order_layout

    override fun initView() {
        //状态栏透明和间距处理
        mMessageListAdapter=activity?.let { MessageListAdapter(it,mlist) }
        activity?.let { StatusBarUtil.darkMode(it) }
        activity?.let { StatusBarUtil.setPaddingSmart(it, cl_bar) }
        recycle_view.adapter=mMessageListAdapter
        recycle_view.layoutManager=LinearLayoutManager(activity)
        iv_left.visibility=View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        iv_left.setOnClickListener(this)
        tv_title.text="通知"
    }
    override fun onClick(v: View?) {
      when(v?.id) {
          R.id.iv_left->{
              activity?.finish()
          }
      }
    }
    override fun lazyLoad() {
    }
}
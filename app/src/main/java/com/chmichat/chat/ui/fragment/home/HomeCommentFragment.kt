package com.chmichat.chat.ui.fragment.home

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseFragment
import com.chmichat.chat.ui.adapter.homeadapter.HomeCommentAdapter
import kotlinx.android.synthetic.main.fragment_home_comment.*

/**
 * 长视频播放里面的评论
 * @Author 20342
 * @Date 2019/8/21 9:57
 */
class HomeCommentFragment : BaseFragment() {
var mlist= arrayListOf("1","1","1","1","1","1","1","1","1","1","1","1")
  var mHomeCommentAdapter: HomeCommentAdapter?=null
    companion object {
        fun getInstance(): HomeCommentFragment {
            val fragment = HomeCommentFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_home_comment

    override fun initView() {
       mHomeCommentAdapter=activity?.let { HomeCommentAdapter(it,mlist) }
        recycle_view.adapter=mHomeCommentAdapter
        recycle_view.layoutManager=LinearLayoutManager(activity)
    }

    override fun lazyLoad() {
    }
}
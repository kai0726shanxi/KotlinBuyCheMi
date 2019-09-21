package com.chmichat.chat.ui.fragment.home

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseFragment
import com.chmichat.chat.bean.PostListEntity
import com.chmichat.chat.mvp.contract.home.PostLongVideoContract
import com.chmichat.chat.ui.adapter.homeadapter.HomeIntrudictionAdapter
import kotlinx.android.synthetic.main.fragment_home_introduction.*

/**
 * @Author 20342
 * @Date 2019/8/21 9:18
 */
class HomeIntroductionFragment:BaseFragment(),PostLongVideoContract.View {



   private var mlist= arrayListOf("1","3","1","1","1","1","1","1","1","1","1","1","1","1")
   private var mHomeIntrudictionAdapter: HomeIntrudictionAdapter?=null
   private  var mPostListEntity:PostListEntity?=null
    companion object {
        fun getInstance(data:PostListEntity?):HomeIntroductionFragment{
            val fragment=HomeIntroductionFragment()
            val bundle=Bundle()
            fragment.arguments=bundle
            fragment.mPostListEntity=data
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


    override fun onPostDetails(data: PostListEntity?) {
        //详情
    }

    override fun onPostRecommendList(data: ArrayList<PostListEntity>?) {
        //相关推荐
    }

    override fun showError(errormsg: String, code: Int) {
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }
}
package com.chmichat.chat.ui.activity.home

import android.support.v7.widget.OrientationHelper
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseActivity
import com.chmichat.chat.ui.adapter.homeadapter.PlayVideoAdapter
import com.chmichat.chat.view.layoutmanagergroup.viewpager.OnViewPagerListener
import com.chmichat.chat.view.layoutmanagergroup.viewpager.ViewPagerLayoutManager
import kotlinx.android.synthetic.main.activity_playvideo.*
import com.chmichat.chat.utils.StatusBarUtil
import android.widget.VideoView







/**
 * @Author 20342
 * @Date 2019/8/17 20:35
 */

class PlayVideoActivity : BaseActivity() {
    private var mPlayVideoAdapter: PlayVideoAdapter? = null
    private var mLayoutManager: ViewPagerLayoutManager? = null
    private var isplaying:Boolean?=null
    private var mlist = arrayListOf("1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1")
    override fun layoutId(): Int {

        return R.layout.activity_playvideo
    }

    override fun initData() {
    }

    override fun initView() {
        StatusBarUtil.darkMode(this)
        mPlayVideoAdapter = PlayVideoAdapter(this, mlist)
        recycle_view.adapter = mPlayVideoAdapter
        mLayoutManager = ViewPagerLayoutManager(this, OrientationHelper.VERTICAL)
        recycle_view.layoutManager = mLayoutManager
        initListener()

    }

    private fun initListener() {

        mLayoutManager!!.setOnViewPagerListener(object : OnViewPagerListener {


            override fun onInitComplete() {

            }

            override fun onPageRelease(isNext: Boolean, position: Int) {


            }


            override fun onPageSelected(position: Int, isBottom: Boolean) {



            }


        })

    }


    override fun start() {
    }

}

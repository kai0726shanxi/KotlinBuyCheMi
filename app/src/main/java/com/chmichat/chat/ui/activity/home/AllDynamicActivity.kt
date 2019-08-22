package com.chmichat.chat.ui.activity.home

import android.graphics.Color
import android.support.v4.app.Fragment
import android.view.View
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseActivity
import com.chmichat.chat.base.BaseFragmentAdapter
import com.chmichat.chat.ui.fragment.discover.DiscoverRecycleVIewFragment
import com.chmichat.chat.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_all_dynamic.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**动态界面
 * @Author 20342
 * @Date 2019/8/22 16:19
 */
class AllDynamicActivity : BaseActivity(),View.OnClickListener {


    var mlist = arrayListOf("全部", "精华", "最新")
    var mFragmentList = ArrayList<Fragment>()
    override fun layoutId(): Int {

        return R.layout.activity_all_dynamic
    }

    override fun initData() {
    }

    override fun initView() {
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this,cl_bar)
         iv_left.visibility=View.VISIBLE
         iv_left.setColorFilter(Color.BLACK)
        iv_left.setOnClickListener(this)
        tv_title.visibility=View.INVISIBLE
        for (index in mlist.indices) {

            mFragmentList.add(DiscoverRecycleVIewFragment.getInstance("5"))
        }
        mViewPager.adapter = BaseFragmentAdapter(supportFragmentManager, mFragmentList, mlist)

        tab_layout.setViewPager(mViewPager)
    }

    override fun start() {
    }

    override fun onClick(v: View?) {

        when(v?.id){
            R.id.iv_left->{
                finish()
            }
        }
    }
}
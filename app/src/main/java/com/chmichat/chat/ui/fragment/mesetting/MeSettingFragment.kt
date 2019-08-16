package com.chmichat.chat.ui.fragment.mesetting

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseFragment
import com.chmichat.chat.base.BaseFragmentAdapter
import com.chmichat.chat.ui.activity.me.SettingActivity
import com.chmichat.chat.ui.activity.me.UserInformationActivity
import com.chmichat.chat.ui.adapter.me.MeBrowseAdapter
import com.chmichat.chat.utils.StatusBarUtil
import kotlinx.android.synthetic.main.fragment_me_layout.*

/**
 * @Author 20342
 * @Date 2019/8/5 11:53
 */
class MeSettingFragment : BaseFragment(),View.OnClickListener {


    private var mMeBrowseAdapter: MeBrowseAdapter? = null
    private val mlist = arrayListOf("1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1")
    private val mTabTitleList = ArrayList<String>()

    private val mFragmentList = ArrayList<Fragment>()

    companion object {
        fun getInstance(): MeSettingFragment {
            val fragment = MeSettingFragment()
            val bundle = Bundle()
            fragment.arguments = bundle

            return fragment

        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_me_layout

    override fun initView() {
        //状态栏透明和间距处理
        activity?.let { StatusBarUtil.darkMode(it) }
        activity?.let { StatusBarUtil.setPaddingSmart(it, iv_setting) }
       // scrollView.setNeedScroll(true)
        mMeBrowseAdapter = activity?.let { MeBrowseAdapter(it, mlist) }
        recycle_view.adapter = mMeBrowseAdapter
        recycle_view.layoutManager = linearLayoutManager
        //小视频       VLOG       收藏
        mTabTitleList.add("动态")
        mTabTitleList.add("小视频")
        mTabTitleList.add("VLOG")
        mTabTitleList.add("收藏")
        for (index in mTabTitleList.indices) {

            mFragmentList.add(MeTabFragment.getInstance("$index"))
        }
        //mTabTitleList .mapTo(mFragmentList) { MeTabFragment.getInstance("") }
        mViewPager.adapter = BaseFragmentAdapter(childFragmentManager, mFragmentList, mTabTitleList)
        me_tabLayout.setupWithViewPager(mViewPager)
        iv_setting.setOnClickListener(this)
        tv_edit.setOnClickListener(this)
    }

    private val linearLayoutManager by lazy {
        LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

    }

    override fun lazyLoad() {
    }

    override fun onClick(v: View?) {
      when{
          v?.id==R.id.iv_setting->{
              startActivity(Intent(activity,SettingActivity::class.java))
          }
          v?.id==R.id.tv_edit->{
              startActivity(Intent(activity, UserInformationActivity::class.java))

          }

      }
    }

}
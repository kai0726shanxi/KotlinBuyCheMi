package com.chmichat.chat.ui.fragment.mesetting

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chmichat.chat.Constants
import com.chmichat.chat.R
import com.chmichat.chat.api.UrlConstant
import com.chmichat.chat.base.BaseFragment
import com.chmichat.chat.base.BaseFragmentAdapter
import com.chmichat.chat.bean.RecentBrowseEntity
import com.chmichat.chat.bean.UserBean
import com.chmichat.chat.glide.GlideApp
import com.chmichat.chat.mvp.contract.me.MeSettingContract
import com.chmichat.chat.mvp.presenter.me.MeSettingPresenter
import com.chmichat.chat.showToast
import com.chmichat.chat.ui.activity.me.SettingActivity
import com.chmichat.chat.ui.activity.me.UserInformationActivity
import com.chmichat.chat.ui.adapter.me.MeBrowseAdapter
import com.chmichat.chat.utils.SpUtil
import com.chmichat.chat.utils.StatusBarUtil
import kotlinx.android.synthetic.main.fragment_me_layout.*

/**
 * @Author 20342
 * @Date 2019/8/5 11:53
 */
class MeSettingFragment : BaseFragment(), MeSettingContract.View, View.OnClickListener {


    private val mPresenter by lazy { MeSettingPresenter() }

    private var mMeBrowseAdapter: MeBrowseAdapter? = null
    private var mRecentlist=ArrayList<RecentBrowseEntity>()
    private val mTabTitleList = ArrayList<String>()

    private val mFragmentList = ArrayList<Fragment>()
    private var map = mapOf<String, String>()

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
        mPresenter.attachView(this)
        activity?.let { StatusBarUtil.darkMode(it) }
        activity?.let { StatusBarUtil.setPaddingSmart(it, iv_setting) }
        // scrollView.setNeedScroll(true)
        mMeBrowseAdapter = activity?.let { MeBrowseAdapter(it, mRecentlist) }
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
        when {
            v?.id == R.id.iv_setting -> {
                startActivity(Intent(activity, SettingActivity::class.java))
            }
            v?.id == R.id.tv_edit -> {
                startActivity(Intent(activity, UserInformationActivity::class.java))

            }

        }
    }


    override fun onStart() {
        super.onStart()
        mPresenter.getUserInfo()
        mPresenter.getRecentBroese(map)

    }

    override fun showError(msg: String, errorCode: Int) {
        ShowErrorMes(msg,errorCode)
    }

    override fun setUserInfo(userBean: UserBean?) {
        //用户信息
        SpUtil.putObject(activity,Constants.USERBEAN,userBean)
        GlideApp.with(this)
                .load(UrlConstant.BASE_URL_IMAGE+userBean?.id+".png")
                .placeholder(R.mipmap.head_ic)
                .into(iv_head)
        tv_name.text=userBean?.nickname
        tv_num.text=""+userBean?.postsNum+" 发帖数 | "+userBean?.praiseTotal+" 获赞数"
        tv_motto.text=userBean?.introduction


    }

    override fun OnRecentBrowse(rb: ArrayList<RecentBrowseEntity>?) {
        //最近常逛
      if (rb!=null){
          mRecentlist= rb!!
          mMeBrowseAdapter?.addData(rb)

      }



    }

    override fun showLoading() {

    }

    override fun dismissLoading() {

    }

}
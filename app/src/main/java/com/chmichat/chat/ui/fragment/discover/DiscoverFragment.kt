package com.chmichat.chat.ui.fragment.discover

import android.os.Bundle
import android.support.v4.app.Fragment
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseFragment
import com.chmichat.chat.base.BaseFragmentAdapter
import com.chmichat.chat.bean.ForumListEntity
import com.chmichat.chat.mvp.contract.discover.DiscoverContract
import com.chmichat.chat.mvp.presenter.discover.DiscoverPresenter
import com.chmichat.chat.showToast
import com.chmichat.chat.utils.StatusBarUtil
import kotlinx.android.synthetic.main.fragment_discover_new.*

/**
 * 发现模块
 * @Author 20342
 * @Date 2019/8/5 11:30
 */
class DiscoverFragment : BaseFragment(),DiscoverContract.View {

    private val mPresenter:DiscoverPresenter by lazy { DiscoverPresenter() }
    private val mTitles = ArrayList<String>()
    private var mForumList=ArrayList<ForumListEntity>()
    private val mFragmentList = ArrayList<Fragment>()
    private var map=HashMap<String,String>()


    companion object {
        fun getInstance(): DiscoverFragment {
            val fragment = DiscoverFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }


    override fun getLayoutId(): Int = R.layout.fragment_discover_new

    override fun initView() {
        //状态栏透明和间距处理
        mPresenter.attachView(this)
        activity?.let { StatusBarUtil.darkMode(it) }
        activity?.let { StatusBarUtil.setPaddingSmart(it, tab_layout) }
        tab_layout.maxTxtSize=22
        tab_layout.minTxtSize=14

    }

    override fun lazyLoad() {
        mPresenter.getForumlist(map)
    }
    override fun onForumlist(data: ArrayList<ForumListEntity>?,pagetotla:Int?) {

        if (data!=null){
            mForumList=data

           if (mTitles!=null&&mTitles.size>0){
               mTitles.clear()
           }
          for (item in mForumList){

              item.sectionName?.let { mTitles.add(it) }


          }

            for (index in mTitles.indices) {

                mForumList[index].id?.let { DiscoverTabFragment.getInstance("$index", it) }?.let { mFragmentList.add(it) }
            }
            mViewPager.adapter = BaseFragmentAdapter(childFragmentManager, mFragmentList, mTitles)

            tab_layout.setViewPager(mViewPager)

        }

    }

    override fun showError(errorMsg: String, errorCode: Int) {
        showToast(errorMsg)
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

}
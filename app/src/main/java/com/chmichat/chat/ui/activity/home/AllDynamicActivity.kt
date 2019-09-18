package com.chmichat.chat.ui.activity.home

import android.graphics.Color
import android.support.v4.app.Fragment
import android.view.View
import com.chmichat.chat.Constants
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseActivity
import com.chmichat.chat.base.BaseFragmentAdapter
import com.chmichat.chat.bean.ForumListEntity
import com.chmichat.chat.glide.GlideApp
import com.chmichat.chat.mvp.contract.home.AllDynamicContract
import com.chmichat.chat.mvp.presenter.home.AllDynamicPresenter
import com.chmichat.chat.showToast
import com.chmichat.chat.ui.fragment.discover.DiscoverRecycleVIewFragment
import com.chmichat.chat.utils.SpUtil
import com.chmichat.chat.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_all_dynamic.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**动态界面
 * @Author 20342
 * @Date 2019/8/22 16:19
 */
class AllDynamicActivity : BaseActivity(), AllDynamicContract.View, View.OnClickListener {

    private val mPresenter: AllDynamicPresenter by lazy { AllDynamicPresenter() }
    private var mlist = arrayListOf("全部", "精华", "最新")
    private var mFragmentList = ArrayList<Fragment>()
    private var mForumListEntity:ForumListEntity?=null

    override fun layoutId(): Int {
        return R.layout.activity_all_dynamic
    }

    override fun initData() {
        mForumListEntity= intent.getSerializableExtra(Constants.KEYNAME) as ForumListEntity?
    }

    override fun initView() {
        mPresenter.attachView(this)
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        iv_left.setOnClickListener(this)
        tv_title.visibility = View.INVISIBLE
        for (index in mlist.indices) {

            mFragmentList.add(DiscoverRecycleVIewFragment.getInstance("$index",mForumListEntity?.id,"bbs"))
        }
        mViewPager.adapter = BaseFragmentAdapter(supportFragmentManager, mFragmentList, mlist)

        tab_layout.setViewPager(mViewPager)
    }

    override fun start() {
        mPresenter.getDynamicDetails(mForumListEntity?.id.toString())
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.iv_left -> {
                finish()
            }
        }
    }


    override fun OnDynamicDetails(data: ForumListEntity?) {
        if (data!=null){
            GlideApp.with(this)
                    .load(data.sectionIcon)
                    .placeholder(R.mipmap.moren_icon)
                    .into(iv_head)
            tv_tag.text=data.sectionName
            if (data.reading_num.isNullOrEmpty()){
                tv_read.text="阅读 0  |  帖子 "+data.total

            }else{
                tv_read.text="阅读 "+data.reading_num+"  |  帖子 "+data.total

            }


        }

    }

    override fun showError(errmsg: String, code: Int) {
        showToast(errmsg)
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {
    }
}
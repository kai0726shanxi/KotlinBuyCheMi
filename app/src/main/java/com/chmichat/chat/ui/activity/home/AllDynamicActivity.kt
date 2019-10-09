package com.chmichat.chat.ui.activity.home

import android.graphics.Color
import android.graphics.drawable.Drawable
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
    private var mId: Int? = 0
    private var drawableTop: Drawable? = null
    private var drawableTopcheck: Drawable? = null
    private var map=HashMap<String,String>()
    private var mForumListEntity:ForumListEntity?=null


    override fun layoutId(): Int {
        return R.layout.activity_all_dynamic
    }

    override fun initData() {
        mId = intent.getIntExtra(Constants.KEYNAME, 0)
    }

    override fun initView() {
        mPresenter.attachView(this)
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        iv_left.setOnClickListener(this)
        tv_collect.setOnClickListener(this)
        tv_title.visibility = View.INVISIBLE
        drawableTop = resources.getDrawable(R.mipmap.dynamic_cang_no)
        drawableTopcheck = resources.getDrawable(R.mipmap.dynamic_cang)
        for (index in mlist.indices) {

            mFragmentList.add(DiscoverRecycleVIewFragment.getInstance("$index", mId, "bbs"))
        }
        mViewPager.adapter = BaseFragmentAdapter(supportFragmentManager, mFragmentList, mlist)

        tab_layout.setViewPager(mViewPager)
    }

    override fun start() {
        mPresenter.getDynamicDetails(mId.toString())
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.iv_left -> {
                finish()
            }
            R.id.tv_collect->{
                if (mForumListEntity!=null){
                    map.clear()
                    map["sectionId"]=mForumListEntity?.id.toString()
                    if (mForumListEntity?.isCollection=="1"){

                        mPresenter.getConcelCollectData(map)
                    }else{
                        mPresenter.getCollectData(map)


                    }
                }else{
                    showToast("稍后重试~~")
                }
            }
        }
    }


    override fun OnDynamicDetails(data: ForumListEntity?) {
        if (data != null) {
            mForumListEntity=data
            GlideApp.with(this)
                    .load(data.sectionIcon)
                    .placeholder(R.mipmap.moren_icon)
                    .into(iv_head)
            tv_tag.text = data.sectionName
            if (data.reading_num.isNullOrEmpty()) {
                tv_read.text = "阅读 0  |  帖子 " + data.total

            } else {
                tv_read.text = "阅读 " + data.reading_num + "  |  帖子 " + data.total

            }
            if (data.isCollection == "1") {
                tv_collect.setCompoundDrawablesWithIntrinsicBounds(null, drawableTopcheck, null, null)
                tv_collect.compoundDrawablePadding = 4
            } else {

                tv_collect.setCompoundDrawablesWithIntrinsicBounds(null, drawableTop, null, null)
                tv_collect.compoundDrawablePadding = 4
            }

        }

    }

    override fun showError(errmsg: String, code: Int) {
      ShowErrorMes(errmsg,code)
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {
    }

    override fun onCollect(date: String?) {
        //收藏成功
        showToast("收藏成功")
        tv_collect.setCompoundDrawablesWithIntrinsicBounds(null, drawableTopcheck, null, null)
       mForumListEntity?.isCollection="1"
    }

    override fun onCancleCollect(date: String?) {
        //取消收藏成功
        showToast("取消收藏")

        tv_collect.setCompoundDrawablesWithIntrinsicBounds(null, drawableTop, null, null)
        mForumListEntity?.isCollection="0"

    }

}
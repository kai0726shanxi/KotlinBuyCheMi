package com.chmichat.chat.ui.activity.add

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chmichat.chat.Constants
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseActivity
import com.chmichat.chat.bean.ForumListEntity
import com.chmichat.chat.mvp.contract.add.ChoseForumContract
import com.chmichat.chat.mvp.presenter.add.ChoseForumPresenter
import com.chmichat.chat.showToast
import com.chmichat.chat.ui.adapter.add.ChoseForumAdapter
import com.chmichat.chat.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_chose_forum.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * 选择论坛的界面
 * @Author 20342
 * @Date 2019/8/15 17:00
 */
class ChoseForumActivity : BaseActivity(),ChoseForumContract.View, View.OnClickListener {


    private val mPresenter by lazy { ChoseForumPresenter() }
    private var mChoseForumAdapter: ChoseForumAdapter? = null
    private val mlist = ArrayList<ForumListEntity>()
    private var map=HashMap<String,String?>()
    private var mtype:String?=""
    override fun layoutId(): Int {

        return R.layout.activity_chose_forum
    }
    override fun initData() {
        mtype=intent.getStringExtra(Constants.KEYTYPE)
    }

    override fun initView() {
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        mPresenter.attachView(this)
        iv_left.setOnClickListener(this)
        tv_title.text="选择论坛"
        mChoseForumAdapter = ChoseForumAdapter(this, mlist)
        recycle_view.adapter = mChoseForumAdapter
        recycle_view.layoutManager = LinearLayoutManager(this)
        mChoseForumAdapter?.setOnTagItemClickListener {
            var intent = Intent()
            intent.putExtra(Constants.KEYNAME, it.sectionName)
            intent.putExtra(Constants.KEYID, it.id.toString())
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

    }

    override fun start() {
        map.clear()
        if (mtype!=null&&!mtype.equals("")){
            map["postType"]=mtype

        }
        mPresenter.getForumlist(map)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_left -> {

                finish()
            }
        }
    }


    override fun onForumlist(data: ArrayList<ForumListEntity>?) {
        if (data!=null){
            mChoseForumAdapter?.addData(data)
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
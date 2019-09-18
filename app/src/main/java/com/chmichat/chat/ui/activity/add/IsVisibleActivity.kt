package com.chmichat.chat.ui.activity.add

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chmichat.chat.Constants
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseActivity
import com.chmichat.chat.bean.IsVisibleEntity
import com.chmichat.chat.ui.adapter.add.IsVisibleAdapter
import com.chmichat.chat.utils.SpUtil
import com.chmichat.chat.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_isvisible.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * @Author 20342
 * @Date 2019/9/11 18:20
 */
class IsVisibleActivity : BaseActivity() {
    private var mIsVisibleAdapter: IsVisibleAdapter? = null
    private val mlist = ArrayList<IsVisibleEntity>()
    override fun layoutId(): Int {
        return R.layout.activity_isvisible
    }

    override fun initData() {
    }

    override fun initView() {
        mlist.add(IsVisibleEntity("公开", "所有人可见", "0"))
        mlist.add(IsVisibleEntity("私密", "仅自己可见", "1"))
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        tv_title.setTextColor(Color.BLACK)
        tv_title.text = "谁可以看"
        mIsVisibleAdapter = IsVisibleAdapter(this, mlist)
        recycle_view.adapter = mIsVisibleAdapter
        recycle_view.layoutManager = LinearLayoutManager(this)
        mIsVisibleAdapter?.setOnTagItemClickListener { tag, i ->
            mIsVisibleAdapter?.mPosition = i
            SpUtil.putInt(this,Constants.ISVISIBLE,i)
            mIsVisibleAdapter?.notifyDataSetChanged()
            var intent = Intent()
            if (i==0){
                intent.putExtra(Constants.KEYNAME, "公开")

            }else{
                intent.putExtra(Constants.KEYNAME, "私密")

            }
            setResult(Activity.RESULT_OK, intent)
            finish()

        }
    }

    override fun start() {
    }
}
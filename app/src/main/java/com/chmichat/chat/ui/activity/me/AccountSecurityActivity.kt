package com.chmichat.chat.ui.activity.me

import android.content.Intent
import android.graphics.Color
import android.view.View
import com.chmichat.chat.Constants
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseActivity
import com.chmichat.chat.showToast
import com.chmichat.chat.ui.activity.LoginActivity
import com.chmichat.chat.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_accountsecur_layout.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**账号安全
 * @Author 20342
 * @Date 2019/8/13 16:42
 */
class AccountSecurityActivity : BaseActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when {
            v?.id == R.id.item_one -> {

                var intent= Intent(this,LoginActivity::class.java)
                intent.putExtra(Constants.KEYTYPE,"psw")
                startActivity(intent)
               // showToast("点击")
            }
            v?.id==R.id.iv_left->{
                finish()
            }


        }
    }

    override fun layoutId(): Int {


        return R.layout.activity_accountsecur_layout
    }


    override fun initData() {

    }

    override fun initView() {
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        cl_bar.setBackgroundColor(Color.WHITE)
        tv_title.setTextColor(Color.BLACK)
        tv_title.text="账号与安全"
        item_one.setOnClickListener(this)
        iv_left.setOnClickListener(this)
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)
    }

    override fun start() {
    }
}
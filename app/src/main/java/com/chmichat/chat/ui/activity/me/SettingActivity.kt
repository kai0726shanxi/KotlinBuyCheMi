package com.chmichat.chat.ui.activity.me

import android.content.Intent
import android.graphics.Color
import android.view.View
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseActivity
import com.chmichat.chat.ui.activity.LoginActivity
import com.chmichat.chat.ui.dialog.ClearDialog
import com.chmichat.chat.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_setting_layout.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**设置界面
 * @Author 20342
 * @Date 2019/8/12 15:50
 */
class SettingActivity : BaseActivity(), View.OnClickListener{


    override fun onClick(v: View?) {
        when {
            v?.id == R.id.tv_account -> {
                //账户安全
                startActivity(Intent(this,AccountSecurityActivity::class.java))
            }
            v?.id == R.id.tv_message -> {
                //消息提醒
                startActivity(Intent(this,MessageNotificationActivity::class.java))
            }
            v?.id == R.id.tv_clear -> {

                if (!mDialog!!.isShowing){
                    mDialog!!.show()
                }

                //清除缓存
            }
            v?.id == R.id.tv_about -> {
                //关于百料论坛
                startActivity(Intent(this,AboutUsActivity::class.java))
            }
            v?.id == R.id.tv_help -> {
                //帮助

            }
            v?.id == R.id.iv_left -> {
                //左边返回按钮
                finish()
            }
            v?.id==R.id.tv_logout->{

                startActivity(Intent(this,LoginActivity::class.java))
            }


        }
    }

    override fun layoutId(): Int = R.layout.activity_setting_layout

    override fun initData() {
    }
    private val mDialog:ClearDialog by lazy {

        ClearDialog(this)
    }
    override fun initView() {
        //状态栏透明和间距处理
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)
        iv_left.visibility=View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        cl_bar.setBackgroundColor(Color.WHITE)
        tv_title.setTextColor(Color.BLACK)
        tv_title.text="设置"
        iv_left.setOnClickListener(this)
        tv_logout.setOnClickListener(this)
        tv_account.setOnClickListener(this)
        tv_message.setOnClickListener(this)
        tv_clear.setOnClickListener(this)
        tv_about.setOnClickListener(this)
        tv_help.setOnClickListener(this)

         mDialog.setOnClickListener(View.OnClickListener { v ->
                           if(v.id == R.id.dialog_cancel){
                               mDialog.dismiss()
                           }
                           if (v.id == R.id.dialog_confirm){
                               mDialog.dismiss()

                           }
                       })


    }


    override fun start() {
    }
}
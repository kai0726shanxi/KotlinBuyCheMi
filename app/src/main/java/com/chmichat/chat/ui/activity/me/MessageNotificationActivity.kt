package com.chmichat.chat.ui.activity.me

import android.graphics.Color
import android.view.View
import android.widget.CompoundButton
import android.widget.Switch
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseActivity
import com.chmichat.chat.bean.NotificationManagerEntity
import com.chmichat.chat.mvp.contract.NotificationManagerContract
import com.chmichat.chat.mvp.presenter.NotificationMangerPresenter
import com.chmichat.chat.showToast
import com.chmichat.chat.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_messagenotification.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * @Author 20342
 * 消息通知
 * @Date 2019/8/13 17:21
 */
class MessageNotificationActivity:BaseActivity(),NotificationManagerContract.View,View.OnClickListener, CompoundButton.OnCheckedChangeListener {


    private val mPresenter by lazy { NotificationMangerPresenter() }
    override fun dismissLoading() {

    }

    override fun showError(errorMsg: String, errorCode: Int) {
    showToast("$errorMsg>>$errorCode")
    }

    override fun SwitchDetails(data: NotificationManagerEntity?) {
        //详情
        s_comment.isChecked=data!!.comments
        s_zan.isChecked=data!!.praise
        s_cai.isChecked=data!!.tread

    }

    override fun SwitchTread(str: Boolean?) {

    }

    override fun SwitchComments(str: Boolean?) {
    }

    override fun SwitchPraise(str: Boolean?) {
    }

    override fun showLoading() {
    }



    override fun onClick(v: View?) {
        when{
            v?.id==R.id.iv_left->{
                finish()
            }
            v?.id==R.id.s_cai->{
                mPresenter.SwitchTread(s_cai.isChecked)

            }
            v?.id==R.id.s_zan->{
                mPresenter.SwitchPraise(s_zan.isChecked)

            }
            v?.id==R.id.s_comment->{
                mPresenter.SwitchComments(s_comment.isChecked)

            }
        }
    }

    override fun layoutId(): Int = R.layout.activity_messagenotification
    override fun initData() {
    }

    override fun initView() {
        mPresenter.attachView(this)
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this,cl_bar)
        iv_left.visibility=View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        tv_title.setTextColor(Color.BLACK)
        cl_bar.setBackgroundColor(Color.WHITE)
        tv_title.text="消息通知"
        iv_left.setOnClickListener(this)
        s_cai.setOnClickListener(this)
        s_comment.setOnClickListener(this)
        s_zan.setOnClickListener(this)

    }

    override fun start() {

        mPresenter.getSwitchDetails()
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {

     /*   when(buttonView?.id){
            R.id.s_comment->{
            mPresenter.SwitchComments(isChecked)
            }
            R.id.s_cai->{
                mPresenter.SwitchTread(isChecked)

            }
            R.id.s_zan->{
                mPresenter.SwitchPraise(isChecked)

            }
        }
*/
    }
}
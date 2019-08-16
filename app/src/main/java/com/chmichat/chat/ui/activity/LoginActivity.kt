package com.chmichat.chat.ui.activity

import android.os.Handler
import android.os.Message
import android.text.InputType
import android.util.TypedValue
import android.view.View
import com.chmichat.chat.Constants
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseActivity
import com.chmichat.chat.mvp.presenter.LoginPresenter
import com.chmichat.chat.utils.AnimationUtils
import com.chmichat.chat.utils.DisplayManager
import kotlinx.android.synthetic.main.activity_login_register.*

/**
 * @Author 20342
 * @Date 2019/8/12 16:36
 */
class LoginActivity : BaseActivity(), View.OnClickListener {


    private val mPresenter by lazy { LoginPresenter() }

    var isforgetpsd: Boolean? = false//是否是忘记密码
    var isRegist: Boolean? = false//是否是注册界面
    var isEmail: Boolean? = false//是否是email
    private var maxtxtsize = 23
    private var mintxtsize = 13
    var viewlist: MutableList<View>? = ArrayList()//缩放加透明的动画
    var statusYviews: MutableList<View>? = ArrayList()//y轴位移的动画
    var contentViews: MutableList<View>? = ArrayList()//整体向上移的动画
    private var mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                1 -> {
                    et_5.visibility = View.GONE
                    et_6.visibility = View.GONE
                }
                2 -> {

                }
                3 -> {
                    maxtxtsize--
                    mintxtsize++
                    runOnUiThread {
                        tv_check_one.setTextSize(TypedValue.COMPLEX_UNIT_SP, maxtxtsize.toFloat())
                        tv_check_two.setTextSize(TypedValue.COMPLEX_UNIT_SP, mintxtsize.toFloat())
                    }

                    if (mintxtsize != 23) {
                        this.sendEmptyMessageDelayed(3, 35)
                    } else {
                        maxtxtsize = 23
                        mintxtsize = 13
                    }

                }
                4 -> {
                    maxtxtsize--
                    mintxtsize++
                    runOnUiThread {
                        tv_check_two.setTextSize(TypedValue.COMPLEX_UNIT_SP, maxtxtsize.toFloat())
                        tv_check_one.setTextSize(TypedValue.COMPLEX_UNIT_SP, mintxtsize.toFloat())
                    }

                    if (maxtxtsize != 13) {
                        this.sendEmptyMessageDelayed(4, 35)
                    } else {
                        maxtxtsize = 23
                        mintxtsize = 13
                    }
                }


            }

        }
    }


    override fun layoutId(): Int {
        return R.layout.activity_login_register
    }

    override fun initData() {
    }

    override fun initView() {
        iv_left.setOnClickListener(this)
        cl_check.setOnClickListener(this)
        tv_regist.setOnClickListener(this)
        tv_forgetpsw.setOnClickListener(this)
        tv_btn_login.setOnClickListener(this)
        tv_btm.setOnClickListener(this)
    }

    override fun start() {
    }

    override fun onClick(v: View?) {
        //点击事件的监听
        when {
            v?.id == R.id.iv_left -> {

                if (isforgetpsd!!) {
                    isforgetpsd = !isforgetpsd!!
                    isRegist = false
                    isEmail=false
                    showanddismissfsw()

                } else if (isRegist!!) {
                    isRegist = !isRegist!!
                    showanddismissregistr()
                } else {
                    finish()
                }
            }
            v?.id == R.id.cl_check -> {
                if (isforgetpsd!!) {
                    return
                }
                if (!isEmail!!) {
                    isEmail = !isEmail!!
                    setEmailData()
                    mHandler.sendEmptyMessage(3)


                } else {
                    isEmail = !isEmail!!

                    setMobileData()
                    mHandler.sendEmptyMessage(4)

                }

            }
            v?.id == R.id.tv_regist -> {
                if (isforgetpsd!!) {
                    if (!isRegist!!) {
                        isRegist = !isRegist!!
                        tv_regist.text = "手机找回"
                        setEmailData()

                    } else {
                        isRegist = !isRegist!!
                        tv_regist.text = "邮箱找回"
                        setMobileData()

                    }

                } else if (!isRegist!!) {
                    isRegist = !isRegist!!
                    showanddismissregistr()
                }
            }
            v?.id == R.id.tv_forgetpsw -> {

                if (!isforgetpsd!!) {
                    isforgetpsd = !isforgetpsd!!
                    showanddismissfsw()

                }
            }
            v?.id == R.id.tv_btn_login -> {

            }
            v?.id == R.id.tv_btm -> {

            }

        }

    }

    //忘记密码的出入场动画
    private fun showanddismissfsw() {
        statusYviews!!.clear()
        viewlist!!.clear()
        statusYviews!!.add(cl_bottom)
        statusYviews!!.add(et_4)
        statusYviews!!.add(tv_regist)
        statusYviews!!.add(tv_forgetpsw)
        statusYviews!!.add(tv_btn_login)
        viewlist!!.add(et_4)
        viewlist!!.add(item_two)
        if (isforgetpsd!!) {

            mPresenter.TopStatusYAnimaition(statusYviews!!, 0f, (et2.measuredHeight + DisplayManager.dip2px(15F)!!).toFloat())
            mPresenter.SetAnimation(viewlist!!, isforgetpsd!!)
            tv_forgetpsw.visibility = View.INVISIBLE
            tv_regist.text = "邮箱找回"
            tv_check_one.text = "重置密码"
            tv_check_two.visibility = View.INVISIBLE
            tv_check_one.setTextSize(TypedValue.COMPLEX_UNIT_SP, 23f)
            tv_check_two.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13f)
            tv_check_one.alpha = 1.0f
            tv_check_two.alpha = 0.7f
            tv_btm.visibility = View.INVISIBLE
            setMobileData()
        } else {
            mPresenter.TopStatusYAnimaition(statusYviews!!, (et2.measuredHeight + DisplayManager.dip2px(15F)!!).toFloat(), 0f)

            mPresenter.SetAnimation(viewlist!!, isforgetpsd!!)
            tv_forgetpsw.visibility = View.VISIBLE
            tv_regist.text = "立即注册"
            tv_check_two.visibility = View.VISIBLE
            tv_check_one.text = "手机登录"
            tv_btm.visibility = View.VISIBLE
            setMobileData()

        }

    }

    //注册界面的入场和出场动画
    private fun showanddismissregistr() {

        statusYviews!!.clear()
        contentViews!!.clear()
        viewlist!!.clear()
        contentViews!!.add(cl_check)
        contentViews!!.add(scrollView)
        statusYviews!!.add(cl_bottom)
        statusYviews!!.add(et_4)
        statusYviews!!.add(et_5)
        statusYviews!!.add(et_6)
        viewlist!!.add(et_4)
        viewlist!!.add(item_two)
        viewlist!!.add(et_5)
        viewlist!!.add(et_6)

        if (isRegist!!) {
            et_5.visibility = View.VISIBLE
            et_6.visibility = View.VISIBLE
            tv_regist.visibility = View.GONE
            tv_forgetpsw.visibility = View.GONE
            //log图标隐藏
            AnimationUtils.scaleAnimatorDismiss(iv_logo, 1.0f, 0f)
            mPresenter.TopStatusYAnimaition(contentViews!!, 0f, (-iv_logo.measuredHeight).toFloat())
            mPresenter.TopStatusYAnimaition(statusYviews!!, 0f, (et2.measuredHeight + DisplayManager.dip2px(15F)!!).toFloat())
            mPresenter.SetAnimation(viewlist!!, isRegist!!)
            mPresenter.TopStatusYViewAnimaition(tv_btn_login, 0f, ((et2.measuredHeight + DisplayManager.dip2px(45F)!!) * 2).toFloat())
            mPresenter.TopStatusYViewAnimaition(tv_btm, 0f, (-(tv_btm.top - cl_bottom.bottom - DisplayManager.dip2px(30F)!!)).toFloat())
        } else {
            tv_forgetpsw.visibility = View.VISIBLE
            tv_regist.visibility = View.VISIBLE
            AnimationUtils.scaleAnimatorShow(iv_logo, 0f, 1.0f)
            mPresenter.TopStatusYAnimaition(contentViews!!, (-iv_logo.measuredHeight).toFloat(), 0f)
            mPresenter.TopStatusYAnimaition(statusYviews!!, (et2.measuredHeight + DisplayManager.dip2px(15F)!!).toFloat(), 0f)
            mPresenter.SetAnimation(viewlist!!, isRegist!!)
            mPresenter.TopStatusYViewAnimaition(tv_btn_login, ((et2.measuredHeight + DisplayManager.dip2px(45F)!!) * 2).toFloat(), 0f)
            mPresenter.TopStatusYViewAnimaition(tv_btm, (-(tv_btm.top - cl_bottom.bottom - DisplayManager.dip2px(30F)!!)).toFloat(), 0f)
            mHandler.sendEmptyMessageDelayed(1, Constants.ANMATIONSHOWTIME)

        }

    }

    private fun setMobileData() {
        chose_num.visibility = View.VISIBLE
        et1.setText("")
        et2.setText("")
        et_3.setText("")
        et_4.setText("")
        et_5.setText("")
        et_6.setText("")
        et1.hint = "请输入手机号"
        et1.inputType = InputType.TYPE_CLASS_PHONE//电话


    }

    private fun setEmailData() {
        chose_num.visibility = View.GONE
        et1.setText("")
        et2.setText("")
        et_3.setText("")
        et_4.setText("")
        et_5.setText("")
        et_6.setText("")
        et1.hint = "请输入邮箱账号"
        et1.inputType = InputType.TYPE_CLASS_TEXT//
    }
}
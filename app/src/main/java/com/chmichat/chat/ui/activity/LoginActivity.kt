package com.chmichat.chat.ui.activity

import android.content.Intent
import android.os.CountDownTimer
import android.os.Handler
import android.os.Message
import android.text.InputType
import android.text.TextUtils
import android.util.TypedValue
import android.view.View
import com.chmichat.chat.Constants
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseActivity
import com.chmichat.chat.bean.SendMsmEntity
import com.chmichat.chat.bean.UserBean
import com.chmichat.chat.mvp.contract.LoginContract
import com.chmichat.chat.mvp.presenter.LoginPresenter
import com.chmichat.chat.showToast
import com.chmichat.chat.utils.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_login_register.*
import java.util.*
import java.util.regex.Pattern

/**
 * @Author 20342
 * @Date 2019/8/12 16:36
 */
class LoginActivity : BaseActivity(), LoginContract.View, View.OnClickListener {


    private val mPresenter by lazy { LoginPresenter() }
    private var strAeraCode: String? = "+86"

    private var isforgetpsd: Boolean? = false//是否是忘记密码
    private var isRegist: Boolean? = false//是否是注册界面
    private var isEmail: Boolean? = false//是否是email
    private var mtype: String? = ""
    private var map = HashMap<String, String>()
    private var myCountDownTimer: MyCountDownTimer? = null

    private var maxtxtsize = 23
    private var mintxtsize = 13
    private var viewlist: MutableList<View>? = ArrayList()//缩放加透明的动画
    private var statusYviews: MutableList<View>? = ArrayList()//y轴位移的动画
    private var contentViews: MutableList<View>? = ArrayList()//整体向上移的动画
    private var mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                1 -> {
                    et_5.visibility = View.GONE
                    et_6.visibility = View.GONE
                    check_view.visibility = View.GONE
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
        mtype = intent.getStringExtra(Constants.KEYTYPE)

    }

    override fun initView() {

        mPresenter.attachView(this)
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, iv_left)
        iv_left.setOnClickListener(this)
        cl_check.setOnClickListener(this)
        tv_regist.setOnClickListener(this)
        tv_sendcode.setOnClickListener(this)
        tv_forgetpsw.setOnClickListener(this)
        tv_btn_login.setOnClickListener(this)
        tv_btm.setOnClickListener(this)
        chose_num.setOnClickListener(this)
        myCountDownTimer = MyCountDownTimer(120000, 1000)

        if (!TextUtils.isEmpty(mtype) && mtype.equals("psw")) {
            mHandler.postDelayed({
                isforgetpsd = !isforgetpsd!!
                showanddismissfsw()
            }, 50)

        }


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
                    isEmail = false
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
                        isEmail = !isEmail!!
                        isRegist = !isRegist!!
                        tv_regist.text = "手机找回"
                        setEmailData()

                    } else {
                        isEmail = !isEmail!!
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
            v?.id == R.id.tv_btm -> {

            }
            v?.id == R.id.chose_num -> {


                val intent = Intent()
                intent.setClass(this, CountryActivity::class.java)
                startActivityForResult(intent, CountryActivity.REQUESTCODE_FROM_MAIN_TO_PEPELU)
            }
            v?.id == R.id.tv_btn_login -> {


                when {
                    isforgetpsd!! -> {
                        //忘记密码
                        when {
                            et1.text.toString().isNullOrEmpty() -> {

                                showToast("请输入账号")
                                return
                            }
                            et2.text.toString().isNullOrEmpty() -> {
                                showToast("请输入验证码")
                                return
                            }
                            et_3.text.toString().isNullOrEmpty() -> {
                                showToast("请输入密码")
                                return
                            }
                            et_3.text.toString() != et_4.text.toString() -> {
                                showToast("密码不一致")
                                return
                            }
                        }

                        if (!isEmail!!) {
                            //手机
                            try {
                                val psw = RsaUtils.publicKeyEncryption(et_3.text.toString().trim())
                                map.clear()
                                map["email"] = et1.text.toString()
                                map["psw"] = psw
                                map["smsCode"] = et2.text.toString()
                                mPresenter.getForgetPassword(map)
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }


                        } else {
                            //邮箱
                            if (!emailValidation(et1.text.toString())) {
                                showToast("请输入正确的邮箱")
                                return
                            }
                            mPresenter.getForgetPasswordEmail(map)

                        }
                    }
                    isRegist!! -> {
                        //注册
                        if (!isEmail!!) {
                            map["areaCode"] = strAeraCode!!
                            map["mobile"] = et1.text.toString()
                            map["smsCode"] = et2.text.toString()
                            map["psw"] = et_3.text.toString()
                            map["companyName"] = et_5.text.toString()
                            map["position"] = et_6.text.toString()
                            map["appId"]="2"
                            mPresenter.getRegisterMember(map)

                        } else {

                            //邮箱
                            if (!emailValidation(et1.text.toString())) {
                                showToast("请输入正确的邮箱")
                                return
                            }
                            map.clear()
                            map["email"] = et1.text.toString()
                            map["smsCode"] = et2.text.toString()
                            map["psw"] = et_3.text.toString()
                            map["companyName"] = et_5.text.toString()
                            map["position"] = et_6.text.toString()
                            map["appId"]="2"
                            mPresenter.geRegisterEmail(map)

                        }
                    }
                    else -> {
                        //登录
                        if (!isEmail!!) {
                            if (et1.text.toString().isNullOrEmpty() || et_3.text.toString().isNullOrEmpty()) {
                                showToast("手机号或密码不能为空")
                                return

                            }
                            map.clear()
                            map["mobile"] = et1.text.toString()
                            map["psw"] = et_3.text.toString()
                            map["areaCode"] = strAeraCode!!
                            map["appId"]="2"
                            mPresenter.getLoginMember(map)

                        } else {
                            //邮箱或密码不能为空
                            if (et1.text.toString().isNullOrEmpty() || et_3.text.toString().isNullOrEmpty()) {
                                showToast("手机号或密码不能为空")
                                return

                            }

                            if (!emailValidation(et1.text.toString())) {
                                showToast("请输入正确的邮箱")
                                return
                            }

                            map.clear()
                            map["email"] = et1.text.toString()
                            map["psw"] = et_3.text.toString()
                            map["appId"]="2"
                            mPresenter.geteMailLogin(map)

                        }
                    }
                }

            }
            v?.id == R.id.tv_sendcode -> {
                if (!isEmail!!) {
                    try {
                        map.clear()
                        val entity = SendMsmEntity(strAeraCode!!, et1.text.toString().trim())
                        val gson = Gson()
                        val str = gson.toJson(entity)
                        val phone_str = RsaUtils.publicKeyEncryption(str)
                        map.clear()
                        map["smsParam"] = phone_str
                        mPresenter.getSendCode(map)
                    }catch (e:Exception){
                        e.printStackTrace()
                    }

                } else {
                    mPresenter.getSendCodeEmail(et1.text.toString().trim())
                }


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
            cl_type_bottom.visibility = View.INVISIBLE
            tv_btn_login.text = "重置"

            setMobileData()
        } else {
            mPresenter.TopStatusYAnimaition(statusYviews!!, (et2.measuredHeight + DisplayManager.dip2px(15F)!!).toFloat(), 0f)

            mPresenter.SetAnimation(viewlist!!, isforgetpsd!!)
            tv_forgetpsw.visibility = View.VISIBLE
            tv_regist.text = "立即注册"
            tv_check_two.visibility = View.VISIBLE
            tv_check_one.text = "手机登录"
            cl_type_bottom.visibility = View.VISIBLE
            tv_btn_login.text = "登录"

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
            check_view.visibility = View.VISIBLE
            tv_btn_login.text = "注册"

            tv_regist.visibility = View.GONE
            tv_forgetpsw.visibility = View.GONE
            //log图标隐藏
            AnimationUtils.scaleAnimatorDismiss(iv_logo, 1.0f, 0f)
            mPresenter.TopStatusYAnimaition(contentViews!!, 0f, (-iv_logo.measuredHeight).toFloat())
            mPresenter.TopStatusYAnimaition(statusYviews!!, 0f, (et2.measuredHeight + DisplayManager.dip2px(15F)!!).toFloat())
            mPresenter.SetAnimation(viewlist!!, isRegist!!)
            mPresenter.TopStatusYViewAnimaition(tv_btn_login, 0f, ((et2.measuredHeight + DisplayManager.dip2px(45F)!!) * 2).toFloat())
            mPresenter.TopStatusYViewAnimaition(cl_type_bottom, 0f, (-(cl_type_bottom.top - cl_bottom.bottom - DisplayManager.dip2px(45F)!!)).toFloat())
        } else {
            tv_btn_login.text = "登录"

            tv_forgetpsw.visibility = View.VISIBLE
            tv_regist.visibility = View.VISIBLE
            AnimationUtils.scaleAnimatorShow(iv_logo, 0f, 1.0f)
            mPresenter.TopStatusYAnimaition(contentViews!!, (-iv_logo.measuredHeight).toFloat(), 0f)
            mPresenter.TopStatusYAnimaition(statusYviews!!, (et2.measuredHeight + DisplayManager.dip2px(15F)!!).toFloat(), 0f)
            mPresenter.SetAnimation(viewlist!!, isRegist!!)
            mPresenter.TopStatusYViewAnimaition(tv_btn_login, ((et2.measuredHeight + DisplayManager.dip2px(60F)!!) * 2).toFloat(), 0f)
            mPresenter.TopStatusYViewAnimaition(cl_type_bottom, (-(cl_type_bottom.top - cl_bottom.bottom - DisplayManager.dip2px(60F)!!)).toFloat(), 0f)
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

    override fun onDestroy() {


        if (myCountDownTimer != null) {
            myCountDownTimer!!.cancel()
            myCountDownTimer = null
        }
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null)
        }
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null)
        }
        super.onDestroy()
    }


    override fun showError(errorMsg: String, errorCode: Int) {
        //错误信息
        ShowErrorMes(errorMsg,errorCode)
    }

    override fun onloginMember(data: UserBean?) {
        //手机登录
        SpUtil.putObject(this,Constants.USERBEAN,data)
        SpUtil.putString(this,Constants.USERBEANTOKEN,data?.accessToken)
        startActivity(Intent(this,MainActivity::class.java))
        finish()

    }

    override fun onemailLogin(data: UserBean?) {
        //邮箱登录
        SpUtil.putObject(this,Constants.USERBEAN,data)
        SpUtil.putString(this,Constants.USERBEANTOKEN,data?.accessToken)
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }

    override fun onregisterMember(data: UserBean?) {
        //手机注册

        isRegist = !isRegist!!
        showanddismissregistr()

    }

    override fun onregisterEmail(data: UserBean?) {
        //邮箱注册
        isRegist = !isRegist!!
        showanddismissregistr()
    }

    override fun onSendCode(strcode: String?) {
        //发送验证码
        myCountDownTimer!!.start()

    }

    override fun onForgetPassword(str: String?) {
        //忘记密码

        isforgetpsd = false
        showanddismissfsw()
    }

    override fun showLoading() {
        //显示dialog
    }

    override fun dismissLoading() {
//
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CountryActivity.REQUESTCODE_FROM_MAIN_TO_PEPELU) {
            try {
                strAeraCode = data?.getStringExtra("countryNumber")
                chose_num.text = strAeraCode

            } catch (e: Exception) {
                e.printStackTrace()
            }


        }


    }

    /**
     * 邮箱验证
     *
     * @return 是否匹配
     */
    private fun emailValidation(email: String): Boolean {
        val mailRegex: String
        val mailName: String = "^[0-9a-z]+\\w*"
        val mailDomain: String = "([0-9a-z]+\\.)+[0-9a-z]+$"
        // ^表明一行以什么开头；^[0-9a-z]表明要以数字或小写字母开头；\\w*表明匹配任意个大写小写字母或数字或下划线
        // ***.***.***格式的域名，其中*为小写字母或数字;第一个括号代表有至少一个***.匹配单元，而[0-9a-z]$表明以小写字母或数字结尾
        // 邮箱正则表达式      ^[0-9a-z]+\w*@([0-9a-z]+\.)+[0-9a-z]+$
        mailRegex = "$mailName@$mailDomain"
        val pattern = Pattern.compile(mailRegex)
        return pattern.matcher(email).matches()
    }

    //倒计时函数
    private inner class MyCountDownTimer(millisInFuture: Long, countDownInterval: Long) : CountDownTimer(millisInFuture, countDownInterval) {

        //计时过程
        override fun onTick(l: Long) {

            // TODO
            //防止计时过程中重复点击
            tv_sendcode.isClickable = false
            tv_sendcode.text = (l / 1000).toString() + ""


        }

        //计时完毕的方法
        override fun onFinish() {
            //重新给Button设置文字
            tv_sendcode.text = "获取验证码"
            //设置可点击
            tv_sendcode.isClickable = true
        }
    }
}




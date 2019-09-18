package com.chmichat.chat.mvp.presenter

import android.view.View
import com.chmichat.chat.base.BasePresenter
import com.chmichat.chat.mvp.contract.LoginContract
import com.chmichat.chat.mvp.model.LoginModel
import com.chmichat.chat.net.exception.ExceptionHandle
import com.chmichat.chat.utils.AnimationUtils

/**
 * 登录注册的presenter
 * @Author 20342
 * @Date 2019/8/12 16:55
 */
class LoginPresenter : BasePresenter<LoginContract.View>(), LoginContract.Presenter {

    private val loginModel: LoginModel by lazy {
        LoginModel()
    }

    override fun getSendCodeEmail(str: String) {
        //邮箱发送验证码
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = loginModel.getSendCodeEmal(str)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()

                        onSendCode(data.data)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)

    }

    override fun getForgetPasswordEmail(map: Map<String, String>) {
        //邮箱忘记密码

        checkViewAttached()
        mRootView?.showLoading()

        val disposable = loginModel.getForgetPasswordEmail(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()

                        onForgetPassword(data.data)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }

    override fun getLoginMember(map: Map<String, String>) {
        //手机登录
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = loginModel.getLoginMember(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()

                        onloginMember(data.data)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }

    override fun geteMailLogin(map: Map<String, String>) {
        //邮箱登录

        checkViewAttached()
        mRootView?.showLoading()

        val disposable = loginModel.getLoginEmail(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()

                        onemailLogin(data.data)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }

    override fun getRegisterMember(map: Map<String, String>) {
        // 手机注册
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = loginModel.getRegisterMember(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()

                        onregisterMember(data.data)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }

    override fun geRegisterEmail(map: Map<String, String>) {
        //邮箱注册

        checkViewAttached()
        mRootView?.showLoading()

        val disposable = loginModel.geRegisterEmail(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()

                        onregisterEmail(data.data)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }

    override fun getSendCode(map: Map<String, String>) {
//发送验证ma
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = loginModel.getSendCode(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()

                        onSendCode(data.data)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }

    override fun getForgetPassword(map: Map<String, String>) {
        //忘记密码
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = loginModel.getForgetPassword(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()

                        onForgetPassword(data.data)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }

    override fun SetAlphaAnimation(view: View, a: Float, b: Float) {
        AnimationUtils.SetAlphaAnmation(view, a, b)
    }

    override fun TopStatusYAnimaition(list: List<View>, a: Float, b: Float) {
        for (v in list) {
            AnimationUtils.TranslateAnimation(v, a, b, "Y")

        }
    }

    override fun TopStatusYViewAnimaition(v: View, a: Float, b: Float) {
        AnimationUtils.TranslateAnimation(v, a, b, "Y")

    }

    override fun SetAnimation(views: List<View>, b: Boolean) {
        for (v in views) {
            AnimationUtils.SetAnimation(v, b)

        }

    }

}
package com.chmichat.chat.mvp.presenter

import android.view.View
import com.chmichat.chat.base.BasePresenter
import com.chmichat.chat.mvp.contract.LoginContract
import com.chmichat.chat.utils.AnimationUtils

/**
 * 登录注册的presenter
 * @Author 20342
 * @Date 2019/8/12 16:55
 */
class LoginPresenter: BasePresenter<LoginContract.View>(), LoginContract.Presenter {
    override fun SetAlphaAnimation(view: View, a: Float, b: Float) {
        AnimationUtils.SetAlphaAnmation(view,a,b)
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
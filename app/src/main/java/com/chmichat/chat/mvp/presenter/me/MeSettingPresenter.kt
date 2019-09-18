package com.chmichat.chat.mvp.presenter.me

import com.chmichat.chat.base.BasePresenter
import com.chmichat.chat.mvp.contract.me.MeSettingContract
import com.chmichat.chat.mvp.model.me.MeSettingModel
import com.chmichat.chat.net.exception.ExceptionHandle

/**
 * @Author 20342
 * @Date 2019/9/9 13:56
 */
class MeSettingPresenter: BasePresenter<MeSettingContract.View>(),MeSettingContract.Presenter {


    private val settingModel:MeSettingModel by lazy {
        MeSettingModel()
    }

    override fun getUserInfo() {
        //手机登录
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = settingModel.getUserInfo()
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()

                        setUserInfo(data.data)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }
    override fun getRecentBroese(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = settingModel.getRecentBrowse(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()

                        OnRecentBrowse(data.data)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }


}
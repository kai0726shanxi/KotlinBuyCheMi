package com.chmichat.chat.mvp.presenter.home

import com.chmichat.chat.base.BasePresenter
import com.chmichat.chat.mvp.contract.home.AllDynamicContract
import com.chmichat.chat.mvp.model.home.AllDynamicModel
import com.chmichat.chat.net.exception.ExceptionHandle

/**
 * @Author 20342
 * @Date 2019/9/18 15:01
 */
class AllDynamicPresenter:BasePresenter<AllDynamicContract.View>(),AllDynamicContract.Presenter {

   private val allModel:AllDynamicModel by lazy { AllDynamicModel() }
    override fun getDynamicDetails(map:String) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = allModel.getForumDetails(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        OnDynamicDetails(data.data)
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
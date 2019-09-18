package com.chmichat.chat.mvp.presenter.me

import com.chmichat.chat.base.BasePresenter
import com.chmichat.chat.mvp.contract.me.MeCollectContract
import com.chmichat.chat.mvp.model.me.MeCollectModel
import com.chmichat.chat.net.exception.ExceptionHandle

/**
 * @Author 20342
 * @Date 2019/9/16 10:33
 */
class MeCollectPresenter:BasePresenter<MeCollectContract.View>(),MeCollectContract.Presenter {
    private val mCollectModel:MeCollectModel by lazy {
        MeCollectModel()
    }

    override fun getCollectList(str: String) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = mCollectModel.getColletList(str)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()

                        setCollectList(data.data,1)
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
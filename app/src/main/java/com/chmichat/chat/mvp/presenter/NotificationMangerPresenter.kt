package com.chmichat.chat.mvp.presenter

import com.chmichat.chat.base.BasePresenter
import com.chmichat.chat.mvp.contract.NotificationManagerContract
import com.chmichat.chat.mvp.model.NotificationManagerModel
import com.chmichat.chat.net.exception.ExceptionHandle

/**
 * @Author 20342
 * @Date 2019/9/2 15:05
 */
class NotificationMangerPresenter : BasePresenter<NotificationManagerContract.View>(), NotificationManagerContract.Presenter {


    private val SearchModel: NotificationManagerModel by lazy {
        NotificationManagerModel()
    }
    //开关详情
    override fun getSwitchDetails() {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable=SearchModel.mSwichDetails()
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()

                      SwitchDetails(data.data)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }

    //踩开关
    override fun SwitchTread(b: Boolean) {

        checkViewAttached()
        mRootView?.showLoading()

        val disposable=SearchModel.mSwichTread(b)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                      SwitchTread(data.data)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }

    //评论
    override fun SwitchComments(b: Boolean) {

        checkViewAttached()
        mRootView?.showLoading()

        val disposable=SearchModel.mSwichComments(b)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()

                        SwitchComments(data.data)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }

    //点赞
    override fun SwitchPraise(b: Boolean) {

        checkViewAttached()
        mRootView?.showLoading()

        val disposable=SearchModel.mSwichPraise(b)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()

                        SwitchPraise(data.data)
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
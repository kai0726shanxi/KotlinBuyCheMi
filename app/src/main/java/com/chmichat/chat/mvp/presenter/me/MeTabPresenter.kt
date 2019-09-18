package com.chmichat.chat.mvp.presenter.me

import com.chmichat.chat.base.BasePresenter
import com.chmichat.chat.mvp.contract.me.MeTabContract
import com.chmichat.chat.mvp.model.me.MeTabModel
import com.chmichat.chat.net.exception.ExceptionHandle

/**
 * @Author 20342
 * @Date 2019/9/10 9:59
 */
class MeTabPresenter:BasePresenter<MeTabContract.View>(),MeTabContract.Presenter {
    override fun getPostList(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = metabModel.getAllPostList(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()

                        onMyPostList(data.data,data.pageCount)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }


    private val metabModel:MeTabModel by lazy {
        MeTabModel()
    }

    override fun getCollectList(str: String) {
         checkViewAttached()
        mRootView?.showLoading()

        val disposable = metabModel.getColletList(str)
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
    override fun getMyPostList(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = metabModel.getMyPostList(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()

                        onMyPostList(data.data,data.pageCount)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)    }
}
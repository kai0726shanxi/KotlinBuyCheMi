package com.chmichat.chat.mvp.presenter.discover

import com.chmichat.chat.base.BasePresenter
import com.chmichat.chat.mvp.contract.discover.DiscoverContract
import com.chmichat.chat.mvp.model.discover.DiscoverModel
import com.chmichat.chat.net.exception.ExceptionHandle

/**
 * @Author 20342
 * @Date 2019/9/16 14:44
 */
class DiscoverPresenter:BasePresenter<DiscoverContract.View>(),DiscoverContract.Presenter {
    private val discovermodel:DiscoverModel by lazy {
        DiscoverModel()
    }
    override fun getForumlist(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = discovermodel.getForumList(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onForumlist(data.data,data.pageCount)
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
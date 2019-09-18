package com.chmichat.chat.mvp.presenter.discover

import com.chmichat.chat.base.BasePresenter
import com.chmichat.chat.mvp.contract.discover.DiscoverContract
import com.chmichat.chat.mvp.contract.discover.DiscoverTabContract
import com.chmichat.chat.mvp.model.discover.DiscoverTabModel
import com.chmichat.chat.net.exception.ExceptionHandle

/**
 * @Author 20342
 * @Date 2019/9/16 18:30
 */
class DiscoverTabPresenter :BasePresenter<DiscoverTabContract.View>(),DiscoverTabContract.Presenter {


    private val discoverModel: DiscoverTabModel by lazy { DiscoverTabModel() }
    override fun getPostList(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = discoverModel.getpostlist(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onPostList(data.data,data.pageCount)
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
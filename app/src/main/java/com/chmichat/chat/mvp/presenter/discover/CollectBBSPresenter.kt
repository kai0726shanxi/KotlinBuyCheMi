package com.chmichat.chat.mvp.presenter.discover

import com.chmichat.chat.base.BasePresenter
import com.chmichat.chat.mvp.contract.discover.CollectBBSContract
import com.chmichat.chat.mvp.model.discover.CollectBBSModel
import com.chmichat.chat.net.exception.ExceptionHandle

/**
 * @Author 20342
 * @Date 2019/9/19 11:40
 */
class CollectBBSPresenter : BasePresenter<CollectBBSContract.View>(), CollectBBSContract.Presenter {

    private val mCollectModel: CollectBBSModel by lazy { CollectBBSModel() }
    //收藏板块
    override fun getCollectData(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = mCollectModel.getCollect(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onCollect(data.data)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }

    //取消收藏板块
    override fun getConcleData(map: Map<String, String>) {

        checkViewAttached()
        mRootView?.showLoading()

        val disposable = mCollectModel.getCancleCollect(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onCancleCollect(data.data)
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
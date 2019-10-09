package com.chmichat.chat.mvp.presenter.home

import com.chmichat.chat.base.BasePresenter
import com.chmichat.chat.mvp.contract.home.CommentListContract
import com.chmichat.chat.mvp.model.home.CommentListModel
import com.chmichat.chat.net.exception.ExceptionHandle

/**
 * @Author 20342
 * @Date 2019/9/18 18:00
 */
class CommentListPresenter :BasePresenter<CommentListContract.View>(),CommentListContract.Presenter {

    private val commentModel: CommentListModel by lazy {
        CommentListModel()
    }
    override fun getCommentList(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = commentModel.getCommentList(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onCommentlist(data.data,data.pageCount,data.pageTotal)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }

    override fun getPushComment(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = commentModel.getPushComment(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onPushComment(data.data)
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
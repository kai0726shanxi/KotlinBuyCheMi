package com.chmichat.chat.mvp.presenter.home

import com.chmichat.chat.base.BasePresenter
import com.chmichat.chat.mvp.contract.home.PostDetailsContract
import com.chmichat.chat.mvp.model.home.PosteDetailsModel
import com.chmichat.chat.net.exception.ExceptionHandle

/**
 * 帖子详情
 * @Author 20342
 * @Date 2019/9/20 13:43
 */
class PostDetailsPresenter:BasePresenter<PostDetailsContract.View>(),PostDetailsContract.Presenter {


    private val detailsModel:PosteDetailsModel by lazy { PosteDetailsModel() }






    override fun getPostDetails(map: String) {

        checkViewAttached()
        mRootView?.showLoading()

        val disposable = detailsModel.getPostDetails(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onPostDetails(data.data)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)



    }

    override fun getPostRecommendList(map: Map<String, String>) {

        checkViewAttached()
        mRootView?.showLoading()

        val disposable = detailsModel.getPostComment(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        //onPostRecommendList(data.data)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)    }

    override fun getPostCommentList(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = detailsModel.getPostComment(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onPostCommentList(data.data,data.pageCount)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)      }

    override fun getSendComment(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = detailsModel.getSendComment(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onPostSendComment(data.data)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)     }
}
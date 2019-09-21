package com.chmichat.chat.mvp.presenter.home

import com.chmichat.chat.base.BasePresenter
import com.chmichat.chat.mvp.contract.home.PlayVideoContract
import com.chmichat.chat.mvp.model.home.PlayVideoModel
import com.chmichat.chat.net.exception.ExceptionHandle

/**
 * @Author 20342
 * @Date 2019/9/19 11:40
 */
class PlayvideoPresenter : BasePresenter<PlayVideoContract.View>(), PlayVideoContract.Presenter {


    private val mPostModel: PlayVideoModel by lazy { PlayVideoModel() }
    //收藏板块
    override fun getCollectPostData(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = mPostModel.getCollectPost(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onCollectPost(data.data)
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
    override fun getConcelCollectPostData(map: Map<String, String>) {

        checkViewAttached()
        mRootView?.showLoading()

        val disposable = mPostModel.getCollectPost(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onCancelCollectPost(data.data)
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
    override fun getPraisePost(map: Map<String, String>) {

        checkViewAttached()
        mRootView?.showLoading()

        val disposable = mPostModel.getPraisePost(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onpraisePost(data.data)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }

    //取消点赞
    override fun getCancelPraisePost(map: Map<String, String>) {

        checkViewAttached()
        mRootView?.showLoading()

        val disposable = mPostModel.getCancelPraisePost(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onCancelPraisePost(data.data)
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
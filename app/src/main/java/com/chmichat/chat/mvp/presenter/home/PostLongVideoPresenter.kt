package com.chmichat.chat.mvp.presenter.home

import com.chmichat.chat.base.BasePresenter
import com.chmichat.chat.mvp.contract.home.PostLongVideoContract
import com.chmichat.chat.mvp.model.home.PosteDetailsModel
import com.chmichat.chat.mvp.model.home.PosteLongVideoModel
import com.chmichat.chat.net.exception.ExceptionHandle

/**
 * 长视频(VLOG)
 * @Author 20342
 * @Date 2019/9/20 13:43
 */
class PostLongVideoPresenter : BasePresenter<PostLongVideoContract.View>(), PostLongVideoContract.Presenter {


    private val longmodel: PosteLongVideoModel by lazy { PosteLongVideoModel() }

    //详情
    override fun getPostDetails(map:String) {

        checkViewAttached()
        mRootView?.showLoading()

        val disposable = longmodel.getPostDetails(map)
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

    //相关推荐
    override fun getPostRecommendList(map: Map<String, String>) {

        checkViewAttached()
        mRootView?.showLoading()

        val disposable = longmodel.getPostRecommendList(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onPostRecommendList(data.data)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }

    //收藏板块
    override fun getCollectPostData(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = longmodel.getCollectPost(map)
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

        val disposable = longmodel.getCancelCollectPost(map)
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

        val disposable = longmodel.getPraisePost(map)
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

        val disposable = longmodel.getCancelPraisePost(map)
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
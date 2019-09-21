package com.chmichat.chat.mvp.presenter.home

import com.chmichat.chat.base.BasePresenter
import com.chmichat.chat.mvp.contract.home.PostLongVideoContract
import com.chmichat.chat.mvp.model.home.PosteDetailsModel
import com.chmichat.chat.net.exception.ExceptionHandle

/**
 * 长视频(VLOG)
 * @Author 20342
 * @Date 2019/9/20 13:43
 */
class PostLongVideoPresenter : BasePresenter<PostLongVideoContract.View>(), PostLongVideoContract.Presenter {


    private val longmodel: PosteDetailsModel by lazy { PosteDetailsModel() }

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

        val disposable = longmodel.getPostComment(map)
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
        addSubscription(disposable)
    }


}
package com.chmichat.chat.mvp.presenter.home

import com.chmichat.chat.base.BasePresenter
import com.chmichat.chat.mvp.contract.home.HomeForumContract
import com.chmichat.chat.mvp.model.home.HomeForumModel
import com.chmichat.chat.net.exception.ExceptionHandle

/**
 * @Author 20342
 * @Date 2019/9/12 11:45
 */
class HomeForumPresenter : BasePresenter<HomeForumContract.View>(), HomeForumContract.Presenter {


    //综合图文
    override fun getImagePostList(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = homeforummodel.getHotpostlist(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        OnImagePostList(data.data)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }

    /***
     * 顶部论坛
     */
    private val homeforummodel: HomeForumModel by lazy {
        HomeForumModel()
    }

    override fun getForumList(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = homeforummodel.getForumList(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        OnforumList(data.data)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)

    }

    /***
     * 热门帖子
     */
    override fun getHotPostList(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = homeforummodel.getHotpostlist(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onHotPostList(data.data)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }

    /***
     * 论坛动态
     */
    override fun getOnDynamicPostList(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = homeforummodel.getHotpostlist(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        OnDynamicPostList(data.data,data.pageCount)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)    }
}
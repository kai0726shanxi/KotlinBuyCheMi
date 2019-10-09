package com.chmichat.chat.mvp.presenter.home

import com.chmichat.chat.base.BasePresenter
import com.chmichat.chat.mvp.contract.home.SearchForumCintract
import com.chmichat.chat.mvp.model.home.SearchForumModel
import com.chmichat.chat.net.exception.ExceptionHandle

/**
 * @Author 20342
 * @Date 2019/10/8 10:51
 */
class SearchForumPresenter:BasePresenter<SearchForumCintract.View>(),SearchForumCintract.Presenter {
   private val mModel:SearchForumModel by lazy { SearchForumModel() }
    override fun getSearchHots(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = mModel.getSearchForumHots(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onSearchHots(data.data)
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
package com.chmichat.chat.mvp.presenter.home

import com.chmichat.chat.base.BasePresenter
import com.chmichat.chat.mvp.contract.home.SearchResultContract
import com.chmichat.chat.mvp.model.home.SearchResultModel
import com.chmichat.chat.net.exception.ExceptionHandle

/**
 * @Author 20342
 * @Date 2019/10/8 11:37
 */
class SearchResultPresenter:BasePresenter<SearchResultContract.View>(),SearchResultContract.Presenter {

   private val mModel :SearchResultModel by lazy { SearchResultModel() }
    override fun getSearchDatas(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = mModel.getSearchPostList(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onSearchPosts(data.data,data.pageCount)
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
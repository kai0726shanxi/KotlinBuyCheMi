package com.chmichat.chat.mvp.presenter.add

import com.chmichat.chat.base.BasePresenter
import com.chmichat.chat.mvp.contract.add.ChoseForumContract
import com.chmichat.chat.mvp.model.add.ChoseForumModel
import com.chmichat.chat.net.exception.ExceptionHandle

/**
 * @Author 20342
 * @Date 2019/9/10 16:25
 */
class ChoseForumPresenter:BasePresenter<ChoseForumContract.View>(),ChoseForumContract.Presenter {


    private val chosemodel:ChoseForumModel by lazy {
        ChoseForumModel()
    }
    override fun getForumlist(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = chosemodel.getForumList(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onForumlist(data.data)
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
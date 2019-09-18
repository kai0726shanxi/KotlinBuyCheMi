package com.chmichat.chat.mvp.presenter.me

import com.chmichat.chat.base.BasePresenter
import com.chmichat.chat.mvp.contract.me.EditUserInfoContract
import com.chmichat.chat.mvp.model.me.EditUserInfoModel
import com.chmichat.chat.net.exception.ExceptionHandle

/**
 * @Author 20342
 * @Date 2019/9/10 13:36
 */
class EditUserInfoPresenter : BasePresenter<EditUserInfoContract.View>(), EditUserInfoContract.Presenter {

    private val editUserInfoModel: EditUserInfoModel by lazy {
        EditUserInfoModel()
    }

    override fun getEditUser(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = editUserInfoModel.getEditUserInfo(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()

                        onEditUser(data.data)
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
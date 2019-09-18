package com.chmichat.chat.mvp.presenter.add

import com.chmichat.chat.base.BasePresenter
import com.chmichat.chat.mvp.contract.add.ReleaseVideoContract
import com.chmichat.chat.mvp.model.add.ReleaseVideoModel
import com.chmichat.chat.net.exception.ExceptionHandle
import okhttp3.RequestBody

/**
 * @Author 20342
 * @Date 2019/9/10 14:33
 */
class ReleaseVideoPresenter:BasePresenter<ReleaseVideoContract.View>(),ReleaseVideoContract.Presenter {
    override fun getQiNiuToken(map: Map<String, String?>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = releaseVideoModel.getQiNiuToken(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onQiNiuToken(data.data)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }

    private val releaseVideoModel:ReleaseVideoModel by lazy {
      ReleaseVideoModel()
  }

    override fun getImageUrl(body: RequestBody) {
        //上传图片
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = releaseVideoModel.getPushImg(body)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onImageUrl(data.data)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }

    override fun getPushReleaseVideo(map: Map<String, String?>) {
        //发布图文
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = releaseVideoModel.getPushReleaseVideo(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()

                        onPushreleaseViedo(data.data)
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
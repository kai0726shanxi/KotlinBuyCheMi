package com.chmichat.chat.mvp.presenter.add

import com.chmichat.chat.base.BasePresenter
import com.chmichat.chat.mvp.contract.add.ImageTextContract
import com.chmichat.chat.mvp.model.add.ImageTextModel
import com.chmichat.chat.net.exception.ExceptionHandle
import okhttp3.RequestBody

/**
 * @Author 20342
 * @Date 2019/9/10 14:33
 */
class ImageTextPresenter:BasePresenter<ImageTextContract.View>(),ImageTextContract.Presenter {

  private val imgtxtmodel:ImageTextModel by lazy {
      ImageTextModel()
  }

    override fun getImageUrl(body: RequestBody) {
        //上传图片
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = imgtxtmodel.getPushImg(body)
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

    override fun getPushImageText(map: Map<String, String?>) {
        //发布图文
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = imgtxtmodel.getImageTextData(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()

                        onPushImagetext(data.data)
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
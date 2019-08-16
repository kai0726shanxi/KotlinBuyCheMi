package com.chmichat.chat

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.chmichat.chat.net.BaseResponse
import com.chmichat.chat.net.exception.ApiException
import io.reactivex.Observable

/**
 * Created by xuhao on 2017/11/14.
 */

fun Fragment.showToast(content: String): Toast {
    val toast = Toast.makeText(this.activity?.applicationContext, content, Toast.LENGTH_SHORT)
    toast.show()
    return toast
}

fun hintKbTwo(mActivity: Activity) {
    val imm = mActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    if (imm.isActive && mActivity.currentFocus != null) {
        if (mActivity.currentFocus!!.windowToken != null) {
            imm.hideSoftInputFromWindow(mActivity.currentFocus!!.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }
}

fun Context.showToast(content: String): Toast {
    val toast = Toast.makeText(App.context, content, Toast.LENGTH_SHORT)
    toast.show()
    return toast
}


fun View.dip2px(dipValue: Float): Int {
    val scale = this.resources.displayMetrics.density
    return (dipValue * scale + 0.5f).toInt()
}

fun View.px2dip(pxValue: Float): Int {
    val scale = this.resources.displayMetrics.density
    return (pxValue / scale + 0.5f).toInt()
}

fun durationFormat(duration: Long?): String {
    val minute = duration!! / 60
    val second = duration % 60
    return if (minute <= 9) {
        if (second <= 9) {
            "0$minute' 0$second''"
        } else {
            "0$minute' $second''"
        }
    } else {
        if (second <= 9) {
            "$minute' 0$second''"
        } else {
            "$minute' $second''"
        }
    }
}

/**
 * 数据流量格式化
 */
fun Context.dataFormat(total: Long): String {
    var result: String
    var speedReal: Int = (total / (1024)).toInt()
    result = if (speedReal < 512) {
        speedReal.toString() + " KB"
    } else {
        val mSpeed = speedReal / 1024.0
        (Math.round(mSpeed * 100) / 100.0).toString() + " MB"
    }
    return result
}
//根据code判断返回的数据
fun <T> Observable<BaseResponse<T>>.dispatchDefault(): Observable<BaseResponse<T>> =
        this.flatMap { tBaseModel ->
            if (tBaseModel.code == 0) {
                Observable.just(tBaseModel!!)
            } else Observable.error(ApiException(Throwable(tBaseModel.msg), tBaseModel.code))
        }





package com.chmichat.chat

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.support.v4.app.Fragment
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.chmichat.chat.net.BaseResponse
import com.chmichat.chat.net.exception.ApiException
import io.reactivex.Observable
import android.media.MediaMetadataRetriever
import android.util.TypedValue
import com.chmichat.chat.ui.activity.LoginActivity
import java.text.SimpleDateFormat
import java.util.*


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

/** 获取状态栏高度  */
fun getStatusBarHeight(context: Context): Int {
    var result = 24
    val resId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
    result = if (resId > 0) {
        context.resources.getDimensionPixelSize(resId)
    } else {
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                result.toFloat(), Resources.getSystem().displayMetrics).toInt()
    }
    return result
}

fun durationFormat(duration: Long?): String {

    val minute = duration!! / 60
    val second = duration % 60
    return if (minute <= 9) {
        if (second <= 9) {
            "0$minute : 0$second"
        } else {
            "0$minute : $second"
        }
    } else {
        if (second <= 9) {
            "$minute : 0$second"
        } else {
            "$minute ： $second"
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

/**
 * 获取本地视频第一帧
 */
fun Context.GetVieoImage(string: String?): Bitmap? {
    try {
        val media = MediaMetadataRetriever()
        media.setDataSource(string)
        return media.getFrameAtTime(1, MediaMetadataRetriever.OPTION_CLOSEST_SYNC)
    } catch (ex: IllegalArgumentException) {
        ex.printStackTrace()
    }
    return null
}

fun Context.getTime4String(time: String): String {
    //代转日期的字符串格式(输入的字符串格式)
    var inputsdf: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    //获取想要的日期格式(输出的日期格式)
    var outputsdf: SimpleDateFormat = SimpleDateFormat("yyyy/MM/dd")
    var date: Date = inputsdf.parse(time)
    return outputsdf.format(date)
}


//根据code判断返回的数据
fun <T> Observable<BaseResponse<T>>.dispatchDefault(): Observable<BaseResponse<T>> =
        this.flatMap { tBaseModel ->
            if (tBaseModel.code == 0) {
                Observable.just(tBaseModel)
            } else {
                Observable.error(ApiException(Throwable(tBaseModel.msg), tBaseModel.code))

            }
        }





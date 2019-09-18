package com.chmichat.chat.mvp.model.add

import com.chmichat.chat.dispatchDefault
import com.chmichat.chat.net.BaseResponse
import com.chmichat.chat.net.RetrofitManager
import com.chmichat.chat.rx.scheduler.SchedulerUtils
import io.reactivex.Observable
import okhttp3.RequestBody

/**
 * @Author 20342
 * @Date 2019/9/10 14:15
 */
class ImageTextModel {
    /**
     * 上传图片
     */
    fun getPushImg(body:RequestBody): Observable<BaseResponse<ArrayList<String?>>> {


        return RetrofitManager.service.getPushImg(body)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())

    }

    /**
     * 发布图文混排
     */
    fun getImageTextData(map: Map<String,String?>):Observable<BaseResponse<String?>>{


        return RetrofitManager.service.getImageText(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }
}
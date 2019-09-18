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
class ReleaseVideoModel {
    /**
     * 上传图片
     */
    fun getPushImg(body:RequestBody): Observable<BaseResponse<ArrayList<String?>>> {


        return RetrofitManager.service.getPushImg(body)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())

    }

    /**
     * 发布视频
     */
    fun getPushReleaseVideo(map: Map<String,String?>):Observable<BaseResponse<String?>>{


        return RetrofitManager.service.getReleaseVideo(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }
    /**
     * 获取token（七牛）
     */
    fun getQiNiuToken(map: Map<String, String?>):Observable<BaseResponse<String?>>{



        return RetrofitManager.service.getgetToken(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())}

}
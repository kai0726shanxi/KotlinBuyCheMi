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
class ReleasePostModel {
    /**
     * 上传图片
     */
    fun getPushImg(body:RequestBody): Observable<BaseResponse<ArrayList<String?>>> {


        return RetrofitManager.service.getPushImg(body)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())

    }

    /**
     * 发布帖子
     */
    fun getReleasePostData(map: Map<String,String?>):Observable<BaseResponse<String?>>{


        return RetrofitManager.service.getReleasePost(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }
}
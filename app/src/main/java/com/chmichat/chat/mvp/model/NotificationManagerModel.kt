package com.chmichat.chat.mvp.model

import com.chmichat.chat.bean.NotificationManagerEntity
import com.chmichat.chat.dispatchDefault
import com.chmichat.chat.net.BaseResponse
import com.chmichat.chat.net.RetrofitManager
import com.chmichat.chat.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * @Author 20342
 * @Date 2019/9/2 14:45
 */
class NotificationManagerModel {

    /**
     * 开关详情
     */
    fun mSwichDetails(): Observable<BaseResponse<NotificationManagerEntity>> {


        return RetrofitManager.service.getSwitchDetails()
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())

    }

    /**
     * 点赞开关
     */
    fun mSwichPraise(b:Boolean): Observable<BaseResponse<Boolean>> {


        return RetrofitManager.service.getSwitchPraise(b)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())

    }

    /**
     * 评论开关
     */
    fun mSwichComments(b: Boolean): Observable<BaseResponse<Boolean>> {


        return RetrofitManager.service.getSwitchComments(b)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())

    }

    /**
     * 踩开关
     */
    fun mSwichTread(b: Boolean): Observable<BaseResponse<Boolean>> {


        return RetrofitManager.service.getSwitchTread(b)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())

    }

}
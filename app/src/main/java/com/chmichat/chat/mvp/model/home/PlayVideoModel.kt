package com.chmichat.chat.mvp.model.home

import com.chmichat.chat.dispatchDefault
import com.chmichat.chat.net.BaseResponse
import com.chmichat.chat.net.RetrofitManager
import com.chmichat.chat.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * @Author 20342
 * @Date 2019/9/19 11:37
 */
class PlayVideoModel {

    /**
     * 收藏帖子
     */
    fun getCollectPost(map: Map<String,String>): Observable<BaseResponse<String>> {


        return RetrofitManager.service.getCollectionPost(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 取消收藏
     */
    fun getCancelCollectPost(map: Map<String,String>): Observable<BaseResponse<String>> {


        return RetrofitManager.service.getCancelCollectionPost(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }
    /**
     * 点赞
     */
    fun getPraisePost(map: Map<String,String>): Observable<BaseResponse<String>> {


        return RetrofitManager.service.getpraisePost(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }
    /**
     * 取消点赞
     */
    fun getCancelPraisePost(map: Map<String,String>): Observable<BaseResponse<String>> {


        return RetrofitManager.service.getCancelpraisePost(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }

}
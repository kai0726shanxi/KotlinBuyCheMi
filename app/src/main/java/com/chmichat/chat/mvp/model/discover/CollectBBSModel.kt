package com.chmichat.chat.mvp.model.discover

import com.chmichat.chat.dispatchDefault
import com.chmichat.chat.net.BaseResponse
import com.chmichat.chat.net.RetrofitManager
import com.chmichat.chat.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * @Author 20342
 * @Date 2019/9/19 11:37
 */
class CollectBBSModel {

    /**
     * 收藏板块
     */
    fun getCollect(map: Map<String,String>): Observable<BaseResponse<String>> {


        return RetrofitManager.service.getCollectionPlate(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 取消收藏
     */
    fun getCancleCollect(map: Map<String,String>): Observable<BaseResponse<String>> {


        return RetrofitManager.service.getCancleCollectionPlate(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }

}
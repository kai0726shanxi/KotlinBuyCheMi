package com.chmichat.chat.mvp.model.home

import com.chmichat.chat.bean.ForumListEntity
import com.chmichat.chat.dispatchDefault
import com.chmichat.chat.net.BaseResponse
import com.chmichat.chat.net.RetrofitManager
import com.chmichat.chat.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * @Author 20342
 * @Date 2019/9/18 15:00
 */
class AllDynamicModel {

    fun getForumDetails(str:String): Observable<BaseResponse<ForumListEntity>> {

        return RetrofitManager.service.getSectionDetails(str)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }



    /**
     * 收藏板块
     */
    fun getCollectBBs(map: Map<String,String>): Observable<BaseResponse<String>> {


        return RetrofitManager.service.getCollectionPlate(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 取消收藏
     */
    fun getCancelCollectBBs(map: Map<String,String>): Observable<BaseResponse<String>> {


        return RetrofitManager.service.getCancleCollectionPlate(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }
}
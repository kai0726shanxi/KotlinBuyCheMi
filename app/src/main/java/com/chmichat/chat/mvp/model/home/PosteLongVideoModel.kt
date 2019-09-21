package com.chmichat.chat.mvp.model.home

import com.chmichat.chat.bean.PostListEntity
import com.chmichat.chat.dispatchDefault
import com.chmichat.chat.net.BaseResponse
import com.chmichat.chat.net.RetrofitManager
import com.chmichat.chat.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * @Author 20342
 * @Date 2019/9/20 13:35
 */
class PosteLongVideoModel {

    /**
     * 长视频详情
     */
    fun getPostDetails(map: String): Observable<BaseResponse<PostListEntity>> {


        return RetrofitManager.service.getPostDtetails(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 长视频相关推荐
     */
/*    fun getPostRecommendList(map: Map<String,String>): Observable<BaseResponse<PostListEntity>> {


        return RetrofitManager.service.getPostDtetails(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }*/


}
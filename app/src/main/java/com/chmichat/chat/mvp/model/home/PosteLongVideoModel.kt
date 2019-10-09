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
    fun getPostRecommendList(map: Map<String,String>): Observable<BaseResponse<ArrayList<PostListEntity>>> {


        return RetrofitManager.service.getPostRelatedList(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }

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
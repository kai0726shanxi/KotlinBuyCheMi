package com.chmichat.chat.mvp.model.home

import com.chmichat.chat.bean.CommentListEntity
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
class PosteDetailsModel {

    /**
     * 帖子详情
     */
    fun getPostDetails(mid: String): Observable<BaseResponse<PostListEntity>> {


        return RetrofitManager.service.getPostDtetails(mid)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 帖子相关推荐
     */
  /*  fun getPostRecommendList(map: Map<String,String>): Observable<BaseResponse<PostListEntity>> {


        return RetrofitManager.service.getpraisePost(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }*/

    /**
     * 帖子评论列表
     */
    fun getPostComment(map: Map<String,String>): Observable<BaseResponse<ArrayList<CommentListEntity>>> {


        return RetrofitManager.service.getcommentlist(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 帖子发送评论
     */
    fun getSendComment(map: Map<String,String>): Observable<BaseResponse<String>> {


        return RetrofitManager.service.getPushcomment(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }

}
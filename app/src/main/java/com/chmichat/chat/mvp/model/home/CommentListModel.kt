package com.chmichat.chat.mvp.model.home

import com.chmichat.chat.bean.CommentListEntity
import com.chmichat.chat.dispatchDefault
import com.chmichat.chat.net.BaseResponse
import com.chmichat.chat.net.RetrofitManager
import com.chmichat.chat.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * @Author 20342
 * @Date 2019/9/18 17:55
 */
class CommentListModel {
    fun getCommentList(map: Map<String, String>): Observable<BaseResponse<ArrayList<CommentListEntity>>> {

        return RetrofitManager.service.getcommentlist(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }

    fun getPushComment(map: Map<String, String>): Observable<BaseResponse<String>> {

        return RetrofitManager.service.getPushcomment(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }
}
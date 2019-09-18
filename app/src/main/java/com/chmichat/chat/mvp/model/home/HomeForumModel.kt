package com.chmichat.chat.mvp.model.home

import com.chmichat.chat.bean.ForumListEntity
import com.chmichat.chat.bean.PostListEntity
import com.chmichat.chat.dispatchDefault
import com.chmichat.chat.net.BaseResponse
import com.chmichat.chat.net.RetrofitManager
import com.chmichat.chat.rx.scheduler.SchedulerUtils
import io.reactivex.Observable
import java.util.*
import kotlin.collections.ArrayList

/**
 * @Author 20342
 * @Date 2019/9/12 11:41
 */
class HomeForumModel {
    fun getForumList(map: Map<String, String>): Observable<BaseResponse<ArrayList<ForumListEntity>>> {

        return RetrofitManager.service.getSectionlist(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }

    fun getHotpostlist(map: Map<String, String>): Observable<BaseResponse<ArrayList<PostListEntity>>> {

        return RetrofitManager.service.getpostlist(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }


}
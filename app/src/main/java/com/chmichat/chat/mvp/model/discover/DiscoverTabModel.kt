package com.chmichat.chat.mvp.model.discover

import com.chmichat.chat.bean.PostListEntity
import com.chmichat.chat.dispatchDefault
import com.chmichat.chat.net.BaseResponse
import com.chmichat.chat.net.RetrofitManager
import com.chmichat.chat.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * @Author 20342
 * @Date 2019/9/16 18:28
 */
class DiscoverTabModel {

    fun getpostlist(map: Map<String, String>): Observable<BaseResponse<ArrayList<PostListEntity>>> {

        return RetrofitManager.service.getpostlist(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }
}
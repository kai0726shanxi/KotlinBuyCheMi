package com.chmichat.chat.mvp.model.add

import com.chmichat.chat.bean.ForumListEntity
import com.chmichat.chat.dispatchDefault
import com.chmichat.chat.net.BaseResponse
import com.chmichat.chat.net.RetrofitManager
import com.chmichat.chat.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * @Author 20342
 * @Date 2019/9/10 16:21
 */
class ChoseForumModel {
    /**
     * 获取论坛版块
     */
    fun getForumList(map: Map<String,String>): Observable<BaseResponse<ArrayList<ForumListEntity>>> {


        return RetrofitManager.service.getSectionlist(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }
}
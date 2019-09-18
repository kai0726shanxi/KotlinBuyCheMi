package com.chmichat.chat.mvp.model.me

import com.chmichat.chat.bean.RecentBrowseEntity
import com.chmichat.chat.bean.UserBean
import com.chmichat.chat.dispatchDefault
import com.chmichat.chat.net.BaseResponse
import com.chmichat.chat.net.RetrofitManager
import com.chmichat.chat.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * @Author 20342
 * @Date 2019/9/9 13:54
 */
class MeSettingModel {

    /***
     *获取用户信息
     */
    fun getUserInfo(): Observable<BaseResponse<UserBean>> {


        return RetrofitManager.service.getMeUserInfo()
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())

    }
    /***
     *获取用户信息
     */
    fun getRecentBrowse(map:Map<String,String>): Observable<BaseResponse<ArrayList<RecentBrowseEntity>>> {


        return RetrofitManager.service.getRecentBrowse(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())

    }
}
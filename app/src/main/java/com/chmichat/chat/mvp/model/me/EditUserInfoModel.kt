package com.chmichat.chat.mvp.model.me

import com.chmichat.chat.dispatchDefault
import com.chmichat.chat.net.BaseResponse
import com.chmichat.chat.net.RetrofitManager
import com.chmichat.chat.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * @Author 20342
 * @Date 2019/9/10 13:33
 */
class EditUserInfoModel {
    /**
     * 编辑用户信息
     */
    fun getEditUserInfo(map: Map<String,String>): Observable<BaseResponse<String?>> {


        return RetrofitManager.service.getEditUserInfo(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())

    }
}
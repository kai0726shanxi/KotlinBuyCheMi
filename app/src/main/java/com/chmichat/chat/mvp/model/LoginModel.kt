package com.chmichat.chat.mvp.model

import com.chmichat.chat.bean.UserBean
import com.chmichat.chat.dispatchDefault
import com.chmichat.chat.net.BaseResponse
import com.chmichat.chat.net.RetrofitManager
import com.chmichat.chat.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * @Author 20342
 * @Date 2019/9/2 16:29
 */
class LoginModel {

    /**
     * 手机登录
     */
    fun getLoginMember(map:Map<String,String>): Observable<BaseResponse<UserBean>> {


        return RetrofitManager.service.loginMember(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 邮箱登录
     */
    fun getLoginEmail(map:Map<String,String>): Observable<BaseResponse<UserBean>> {


        return RetrofitManager.service.getemailLogin(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())

    }

    /***
     * 手机注册
     */
    fun getRegisterMember(map:Map<String,String>): Observable<BaseResponse<UserBean>> {


        return RetrofitManager.service.registerMember(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())

    }
    /**
     * 邮箱注册
     */
    fun geRegisterEmail(map:Map<String,String>): Observable<BaseResponse<UserBean>> {


        return RetrofitManager.service.registerEmail(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())

    }
    /**
     * 发送验证码手机
     */
    fun getSendCode(map:Map<String,String>): Observable<BaseResponse<String>> {


        return RetrofitManager.service.sendCode(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())

    }
    /**
     * 发送验证码邮箱
     */
    fun getSendCodeEmal(map:String): Observable<BaseResponse<String>> {


        return RetrofitManager.service.getsendEmailCode(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())

    }
    /***
     * 忘记密码(手机)
     */
    fun getForgetPassword(map:Map<String,String>): Observable<BaseResponse<String>> {


        return RetrofitManager.service.ForgetPassword(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())

    }

    /***
     *忘记密码（邮箱）
     */
    fun getForgetPasswordEmail(map:Map<String,String>): Observable<BaseResponse<String>> {


        return RetrofitManager.service.ForgetPasswordEmail(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())

    }
}
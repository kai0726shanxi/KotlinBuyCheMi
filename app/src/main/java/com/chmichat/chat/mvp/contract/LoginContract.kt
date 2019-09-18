package com.chmichat.chat.mvp.contract

import com.chmichat.chat.base.IBaseView
import com.chmichat.chat.bean.UserBean

/**
 * @Author 20342
 * @Date 2019/8/12 16:46
 */
interface LoginContract {

    interface View:IBaseView{

        /**
         * 显示错误信息
         */
        fun showError(errorMsg:String,errorCode:Int)
        /**
         * 手机登录
         */
        fun onloginMember(data: UserBean?)
        /***
         * 邮箱登录
         */
       fun onemailLogin(data: UserBean?)
        /**
         *手机注册
         */
       fun onregisterMember(data: UserBean?)
        /**
         * 邮箱注册
         */
        fun onregisterEmail(data: UserBean?)
        /**
         * 发送验证码
         */

       fun onSendCode(strcode:String?)
        /***
         * 忘记密码
         */
        fun onForgetPassword(str: String?)
    }

    interface Presenter{
     fun TopStatusYAnimaition(list:List<android.view.View>,a:Float,b:Float)
     fun TopStatusYViewAnimaition(v:android.view.View,a:Float,b:Float)
     fun SetAnimation(views:List<android.view.View>,b:Boolean)
     fun SetAlphaAnimation(view: android.view.View,a: Float,b: Float)

     fun getLoginMember(map:Map<String,String>)
     fun geteMailLogin(map:Map<String,String>)
     fun getRegisterMember(map:Map<String,String>)
     fun geRegisterEmail(map:Map<String,String>)
     fun getSendCode(map:Map<String,String>)
     fun getSendCodeEmail(str:String)
     fun getForgetPassword(map:Map<String,String>)
     fun getForgetPasswordEmail(map:Map<String,String>)


    }
}
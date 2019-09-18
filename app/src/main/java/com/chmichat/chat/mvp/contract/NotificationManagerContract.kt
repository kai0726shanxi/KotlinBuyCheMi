package com.chmichat.chat.mvp.contract

import com.chmichat.chat.base.IBaseView
import com.chmichat.chat.bean.NotificationManagerEntity

/**
 * 通知管理
 * @Author 20342
 * @Date 2019/9/2 14:27
 */
interface NotificationManagerContract {

    interface View:IBaseView{

        /**
         * 显示错误信息
         */
        fun showError(errorMsg:String,errorCode:Int)

        /**
         *开关详情
         */
        fun SwitchDetails(data:NotificationManagerEntity?)

        /**
         * 切换踩的开关
         */
        fun SwitchTread(str:Boolean?)
        /**
         * 切换评论的开关
         */
        fun SwitchComments(str:Boolean?)
        /**
         * 切换点赞的开关
         */
        fun SwitchPraise(str:Boolean?)

    }


    interface Presenter {
        /**
         * 切换详情
         */
        fun getSwitchDetails()
        /**
         * 切换踩的开关
         */
        fun SwitchTread(b:Boolean)
        /**
         * 切换评论的开关
         */
        fun SwitchComments(b:Boolean)
        /**
         * 切换点赞的开关
         */
        fun SwitchPraise(b:Boolean)
    }
}
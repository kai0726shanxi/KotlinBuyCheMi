package com.chmichat.chat.mvp.contract.me

import com.chmichat.chat.base.IBaseView
import com.chmichat.chat.bean.RecentBrowseEntity
import com.chmichat.chat.bean.UserBean

/**
 * @Author 20342
 * @Date 2019/9/9 13:48
 */
interface MeSettingContract {
    interface View:IBaseView{
        /**
         * 显示错误信息
         */
        fun showError(msg: String,errorCode:Int)
        /***
         * 用户信息
         */
        fun setUserInfo(userBean: UserBean?)
        fun OnRecentBrowse(rb: ArrayList<RecentBrowseEntity>?)

    }
    interface Presenter{
        /**
         * 用户信息
         */
        fun getUserInfo()

        /**
         * 最近常逛
         */
        fun getRecentBroese(map: Map<String,String>)

    }
}
package com.chmichat.chat.mvp.contract

import com.chmichat.chat.base.IBaseView

/**
 * @Author 20342
 * @Date 2019/8/12 16:46
 */
interface LoginContract {

    interface View:IBaseView{

    }

    interface Presenter{
    fun TopStatusYAnimaition(list:List<android.view.View>,a:Float,b:Float)
     fun TopStatusYViewAnimaition(v:android.view.View,a:Float,b:Float)
     fun SetAnimation(views:List<android.view.View>,b:Boolean)
     fun SetAlphaAnimation(view: android.view.View,a: Float,b: Float)


    }
}
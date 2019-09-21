package com.chmichat.chat.mvp.contract.me

import com.chmichat.chat.base.IBaseView
import com.chmichat.chat.bean.PostListEntity

/**
 * @Author 20342
 * @Date 2019/9/16 10:30
 */
interface MeCollectContract {

    interface View: IBaseView {
        /***
         * 收藏
         */
        fun setCollectList(data:ArrayList<PostListEntity>?, totalsize:Int)
        /**
         * 显示错误信息
         */
        fun showError(errorMsg:String,errorCode:Int)
    }

    interface Presenter{
        /**
         * 收藏
         */
        fun getCollectList(map:Map<String,String>)

    }
}
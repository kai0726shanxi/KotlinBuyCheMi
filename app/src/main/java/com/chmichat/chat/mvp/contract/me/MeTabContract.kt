package com.chmichat.chat.mvp.contract.me

import com.chmichat.chat.base.IBaseView
import com.chmichat.chat.bean.CollectEntity
import com.chmichat.chat.bean.PostListEntity

/**
 * @Author 20342
 * @Date 2019/9/10 9:52
 */
interface MeTabContract {
    interface View:IBaseView{
        /***
         * 收藏
         */
        fun setCollectList(data:ArrayList<CollectEntity>?,totalsize:Int)
        /**
         * 显示错误信息
         */
        fun showError(errorMsg:String,errorCode:Int)
        fun onMyPostList(data: ArrayList<PostListEntity>?,totalpage:Int?)

    }

    interface Presenter{
        /**
         * 收藏
         */
        fun getCollectList(str:String)

        /**
         * 我的帖子
         */
        fun getMyPostList(map: Map<String,String>)
        //全部帖子
        fun getPostList(map: Map<String, String>)

    }
}
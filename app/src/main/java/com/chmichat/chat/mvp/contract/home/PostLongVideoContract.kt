package com.chmichat.chat.mvp.contract.home

import com.chmichat.chat.base.IBaseView
import com.chmichat.chat.bean.CommentListEntity
import com.chmichat.chat.bean.PostListEntity

/**
 * 帖子详情
 * @Author 20342
 * @Date 2019/9/20 13:02
 */
interface PostLongVideoContract {

    interface View:IBaseView{
        fun onPostDetails(data:PostListEntity?)
        fun onPostRecommendList(data: ArrayList<PostListEntity>?)
        fun showError(errormsg:String,code:Int )
    }

    interface Presenter{
        fun getPostDetails(map: String)
        fun getPostRecommendList(map: Map<String, String>)

    }
}
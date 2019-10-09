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
        fun onCollectPost(date: String?)
        fun onCancelCollectPost(date: String?)
        fun onpraisePost(date: String?)
        fun onCancelPraisePost(date: String?)
        fun showError(errormsg:String,code:Int )
    }

    interface Presenter{
        fun getCollectPostData(map: Map<String,String>)
        fun getConcelCollectPostData(map: Map<String, String>)
        fun getPraisePost(map: Map<String, String>)
        fun getCancelPraisePost(map: Map<String, String>)
        fun getPostDetails(map: String)
        fun getPostRecommendList(map: Map<String, String>)
    }
}
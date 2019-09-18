package com.chmichat.chat.mvp.contract.home

import com.chmichat.chat.base.IBaseView
import com.chmichat.chat.bean.ForumListEntity
import com.chmichat.chat.bean.PostListEntity

/**
 * @Author 20342
 * @Date 2019/9/12 11:32
 */
interface HomeForumContract {
    interface View:IBaseView{
        fun OnforumList(data:ArrayList<ForumListEntity>?)
        fun onHotPostList(data: ArrayList<PostListEntity>?)
        fun OnImagePostList(data: ArrayList<PostListEntity>?)
        fun OnDynamicPostList(data: ArrayList<PostListEntity>?,totalpage:Int?)
        fun showError(emg:String,code:Int)
    }
    interface Presenter{
        fun getForumList(map: Map<String,String>)
        fun getHotPostList(map: Map<String, String>)
        fun getImagePostList(map: Map<String, String>)
        fun getOnDynamicPostList(map: Map<String, String>)
    }
}
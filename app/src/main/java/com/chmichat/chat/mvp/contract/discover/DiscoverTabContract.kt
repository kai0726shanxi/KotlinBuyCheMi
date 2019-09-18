package com.chmichat.chat.mvp.contract.discover

import com.chmichat.chat.base.IBaseView
import com.chmichat.chat.bean.PostListEntity

/**
 * @Author 20342
 * @Date 2019/9/16 18:26
 */
interface DiscoverTabContract {
    interface View:IBaseView{
        fun showError(emg:String,code:Int)
        fun onPostList(data: ArrayList<PostListEntity>?,pagetotal:Int?)

    }

    interface Presenter{

        fun getPostList(map: Map<String,String>)
    }
}
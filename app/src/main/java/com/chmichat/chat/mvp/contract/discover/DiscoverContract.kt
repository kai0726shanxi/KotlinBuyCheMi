package com.chmichat.chat.mvp.contract.discover

import com.chmichat.chat.base.IBaseView
import com.chmichat.chat.bean.ForumListEntity

/**
 * @Author 20342
 * @Date 2019/9/16 14:09
 */
interface DiscoverContract {
    interface View:IBaseView{
        //论坛
        fun onForumlist(data:ArrayList<ForumListEntity>?,totalpage:Int?)
        fun showError(errorMsg:String,errorCode:Int)

    }
    interface Presenter{
        fun getForumlist(map: Map<String,String>)
    }
}
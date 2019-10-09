package com.chmichat.chat.mvp.contract.add

import com.chmichat.chat.base.IBaseView
import com.chmichat.chat.bean.ForumListEntity

/**
 * @Author 20342
 * @Date 2019/9/10 16:19
 */
interface ChoseForumContract {
    interface View:IBaseView{
        fun onForumlist(data:ArrayList<ForumListEntity>?)
        fun showError(errorMsg:String,errorCode:Int)

    }
    interface Presenter{
        fun getForumlist(map: Map<String,String?>)
    }
}
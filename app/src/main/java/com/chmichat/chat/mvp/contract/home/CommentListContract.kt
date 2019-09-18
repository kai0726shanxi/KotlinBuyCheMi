package com.chmichat.chat.mvp.contract.home

import com.chmichat.chat.base.IBaseView
import com.chmichat.chat.bean.CommentListEntity

/**
 * 评论
 * @Author 20342
 * @Date 2019/9/18 17:51
 */
interface CommentListContract {
    interface View:IBaseView{
        fun onCommentlist(data:ArrayList<CommentListEntity>?, totalpage:Int?)
        fun onPushComment(data:String?)
        fun showError(emg:String,code:Int)
    }
    interface Presenter{
        fun getCommentList(map: Map<String,String>)
        fun getPushComment(map: Map<String, String>)
    }
}
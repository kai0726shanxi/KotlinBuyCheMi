package com.chmichat.chat.mvp.contract.home

import com.chmichat.chat.base.IBaseView

/**
 * @Author 20342
 * @Date 2019/9/17 17:55
 */
interface PlayVideoContract {
    interface View:IBaseView{
        fun onCollectPost(date: String?)
        fun onCancelCollectPost(date: String?)
        fun onpraisePost(date: String?)
        fun onCancelPraisePost(date: String?)
        fun showError(errmsg:String,code:Int)
    }

    interface Presenter{
        fun getCollectPostData(map: Map<String,String>)
        fun getConcelCollectPostData(map: Map<String, String>)
        fun getPraisePost(map: Map<String, String>)
        fun getCancelPraisePost(map: Map<String, String>)
    }
}
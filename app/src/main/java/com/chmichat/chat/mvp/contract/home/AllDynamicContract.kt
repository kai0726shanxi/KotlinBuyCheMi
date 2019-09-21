package com.chmichat.chat.mvp.contract.home

import com.chmichat.chat.base.IBaseView
import com.chmichat.chat.bean.ForumListEntity

/**
 * @Author 20342
 * @Date 2019/9/18 14:57
 */
interface AllDynamicContract {
    interface View:IBaseView{
        fun onCollect(date: String?)
        fun onCancleCollect(date: String?)
        fun OnDynamicDetails(data:ForumListEntity?)
        fun showError(errmsg:String,code:Int)
    }

    interface Presenter{
        fun getDynamicDetails(map: String)
        fun getCollectData(map: Map<String,String>)
        fun getConcelCollectData(map: Map<String, String>)
    }
}
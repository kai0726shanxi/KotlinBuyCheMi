package com.chmichat.chat.mvp.contract.discover

import com.chmichat.chat.base.IBaseView

/**
 * @Author 20342
 * @Date 2019/9/19 11:34
 */
interface CollectBBSContract {
    interface View:IBaseView{
        fun onCollect(date: String?)
        fun onCancleCollect(date: String?)
        fun showError(errmsg:String,code:Int)

    }

    interface Presenter {
        fun getCollectData(map: Map<String,String>)
        fun getConcleData(map: Map<String, String>)

    }
}
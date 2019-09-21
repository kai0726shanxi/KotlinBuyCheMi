package com.chmichat.chat.mvp.contract.discover

import com.chmichat.chat.base.IBaseView
import java.util.*

/**
 * @Author 20342
 * @Date 2019/9/19 11:34
 */
interface CollectPostContract {
    interface View:IBaseView{
        fun onCollectPost(date: String?)
        fun onCancelCollectPost(date: String?)
        fun showError(errmsg:String,code:Int)

    }

    interface Presenter {
        fun getCollectPostData(map: Map<String,String>)
        fun getConcelCollectPostData(map: Map<String, String>)

    }
}
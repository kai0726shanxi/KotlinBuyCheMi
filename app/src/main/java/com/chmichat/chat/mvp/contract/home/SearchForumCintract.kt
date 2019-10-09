package com.chmichat.chat.mvp.contract.home

import com.chmichat.chat.base.IBaseView
import com.chmichat.chat.bean.SearchGoodsHotBean

/**
 * @Author 20342
 * @Date 2019/10/8 10:45
 */
interface SearchForumCintract {
    interface View: IBaseView {
        fun onSearchHots(date: ArrayList<SearchGoodsHotBean>?)
        fun showError(errmsg:String,code:Int)

    }
    interface Presenter{
        fun getSearchHots(map:Map<String,String>)
    }
}
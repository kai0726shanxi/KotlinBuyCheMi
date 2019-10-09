package com.chmichat.chat.mvp.contract.home

import com.chmichat.chat.base.IBaseView
import com.chmichat.chat.bean.PostListEntity

/**
 * @Author 20342
 * @Date 2019/10/8 11:35
 */
interface SearchResultContract {

    interface View: IBaseView {
        fun onSearchPosts(date: ArrayList<PostListEntity>?,total:Int?)
        fun showError(errmsg:String,code:Int)

    }
    interface Presenter{
        fun getSearchDatas(map:Map<String,String>)
    }
}
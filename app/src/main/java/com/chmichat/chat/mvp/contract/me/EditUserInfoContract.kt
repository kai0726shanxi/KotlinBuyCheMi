package com.chmichat.chat.mvp.contract.me

import com.chmichat.chat.base.IBaseView

/**
 * @Author 20342
 * @Date 2019/9/10 13:30
 */
interface EditUserInfoContract {
    interface View:IBaseView{
        /**
         * 上传数据
         */
        fun onEditUser(str:String?)
        fun  showError(msg:String,code:Int)
    }

    interface Presenter{
        fun getEditUser(map: Map<String,String>)
    }
}
package com.chmichat.chat.mvp.contract.add

import com.chmichat.chat.base.IBaseView
import okhttp3.RequestBody

/**帖子
 * @Author 20342
 * @Date 2019/9/10 14:09
 */
interface ReleasePostContract {
    interface View:IBaseView{
        fun onImageUrl(str:ArrayList<String?>?)
        fun onPushImagetext(str: String?)
        /**
         * 显示错误信息
         */
        fun showError(errorMsg:String,errorCode:Int)
    }
    interface Presenter{
        fun getImageUrl(body: RequestBody)
        fun getPushPost(map:Map<String,String?>)

    }
}
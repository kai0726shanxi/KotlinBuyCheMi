package com.chmichat.chat.mvp.contract.add

import com.chmichat.chat.base.IBaseView
import okhttp3.RequestBody

/**图文
 * @Author 20342
 * @Date 2019/9/10 14:09
 */
interface ReleaseVideoContract {
    interface View:IBaseView{
        fun onImageUrl(str:ArrayList<String?>?)
        fun onPushreleaseViedo(str: String?)
        fun onQiNiuToken(str: String?)
        /**
         * 显示错误信息
         */
        fun showError(errorMsg:String,errorCode:Int)
    }
    interface Presenter{
        fun getImageUrl(body: RequestBody)
        fun getPushReleaseVideo(map:Map<String,String?>)
        fun getQiNiuToken(map: Map<String, String?>)

    }
}
package com.chmichat.chat.mvp.model.me

import com.chmichat.chat.bean.CollectEntity
import com.chmichat.chat.dispatchDefault
import com.chmichat.chat.net.BaseResponse
import com.chmichat.chat.net.RetrofitManager
import com.chmichat.chat.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * @Author 20342
 * @Date 2019/9/16 10:32
 */
class MeCollectModel {


    /***
     *收藏列表
     */
    fun getColletList(str:String): Observable<BaseResponse<ArrayList<CollectEntity>>> {


        return RetrofitManager.service.getCollectionList(str)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())

    }
}
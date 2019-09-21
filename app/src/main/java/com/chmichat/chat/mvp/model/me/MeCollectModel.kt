package com.chmichat.chat.mvp.model.me

import com.chmichat.chat.bean.PostListEntity
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
    fun getColletList(map: Map<String,String>): Observable<BaseResponse<ArrayList<PostListEntity>>> {


        return RetrofitManager.service.getCollectionList(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())

    }
}
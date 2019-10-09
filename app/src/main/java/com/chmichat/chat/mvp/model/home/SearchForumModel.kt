package com.chmichat.chat.mvp.model.home

import com.chmichat.chat.bean.SearchGoodsHotBean
import com.chmichat.chat.dispatchDefault
import com.chmichat.chat.net.BaseResponse
import com.chmichat.chat.net.RetrofitManager
import com.chmichat.chat.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * @Author 20342
 * @Date 2019/10/8 10:48
 */
class SearchForumModel {
    /**
     * 热门推荐
     */
    fun getSearchForumHots(map: Map<String,String>): Observable<BaseResponse<ArrayList<SearchGoodsHotBean>>> {


        return RetrofitManager.service.getGoodsHots(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }
}
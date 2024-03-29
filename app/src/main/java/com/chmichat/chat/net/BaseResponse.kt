package com.chmichat.chat.net

/**
 * Created by xuhao on 2017/11/16.
 * 封装返回的数据
 */
class BaseResponse<T>(val code :Int,
                      val msg:String,
                      val data:T?,
                      val pageTotal:Int,
                      val pageCount:Int)
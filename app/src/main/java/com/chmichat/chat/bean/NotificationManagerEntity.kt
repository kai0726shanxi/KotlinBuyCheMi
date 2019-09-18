package com.chmichat.chat.bean

/**
 * Created by 20342 on 2019-09-02
 *消息通知开关
 * comments  评论
 * praise   点赞
 * tread    踩
 */
data class NotificationManagerEntity(var comments:Boolean,var praise:Boolean,var tread:Boolean)
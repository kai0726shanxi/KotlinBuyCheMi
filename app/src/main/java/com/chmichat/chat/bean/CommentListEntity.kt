package com.chmichat.chat.bean

import java.io.Serializable

/**
 * 评论
 * Created by 20342 on 2019-09-18
 */
data class CommentListEntity(var id:Int?,
                             var commentId:Int?,
                             var postId:Int?,
                             var sendUserId:Int?,
                             var sendUserName:String?,
                             var receiveUserId:Int?,
                             var receiveUserName:String?,
                             var comment:String?,
                             var showComment:String?,
                             var level:String?="",
                             var auditTime:String?,
                             var createTime:String?):Serializable
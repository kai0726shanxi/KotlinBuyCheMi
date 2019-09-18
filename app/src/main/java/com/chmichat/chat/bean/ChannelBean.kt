package com.chmichat.chat.bean

import java.io.Serializable

/**
 * @Author 20342
 * @Date 2019/8/24 15:30
 *

 */
data class ChannelBean(val type: String, val imgUrl: String, var etContent: String, val isMove: Boolean) : Serializable

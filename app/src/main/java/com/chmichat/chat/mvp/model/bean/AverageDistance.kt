package com.chmichat.chat.mvp.model.bean

data class AverageDistance(
    val metric: String,
    val normalizedValue: Double,
    val unit: String,
    val value: Double
)
package com.bw.kf.common.entity

class NewsList: ArrayList<NewsEntity>()
data class NewsEntity(
    val newsId: Int,
    val newsTime: String,
    val newsTitle: String,
    val newsValue: String,
    val tagId: Int,
    val userId: Int
)
package com.example.newsapp.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class News(

    @Json(name = "article_id")
    val id: String,

    val title: String?,

    val description: String?,

    val language: String?

)

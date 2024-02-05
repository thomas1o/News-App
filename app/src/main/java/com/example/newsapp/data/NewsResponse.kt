package com.example.newsapp.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsResponse(

    val status: String,

    val totalResults: Int,

    val results: List<News>

)

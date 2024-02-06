package com.example.newsapp.model

import com.example.newsapp.data.NewsResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private const val BASE_URL = "https://newsdata.io/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface NewsApiService {
    @GET("api/1/news?apikey=pub_36693fa08c2b7c489b95080c59a82506483d3&language=en&country=in&category=top")
    fun getLatestNews(): Call<NewsResponse>

    @GET("api/1/news?apikey=pub_36693fa08c2b7c489b95080c59a82506483d3&language=en")
    fun getNewsUsingKeyword( @Query("q") keyword: String): Call<NewsResponse>
}

object NewsApi {
    val retrofitService : NewsApiService by lazy {
        retrofit.create(NewsApiService::class.java)
    }
}

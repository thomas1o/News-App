package com.example.newsapp.model

import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.newsapp.data.News
import com.example.newsapp.data.NewsDatabase
import com.example.newsapp.data.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepository(private val database: NewsDatabase) {

    fun getCars(): LiveData<List<News>> {
        return database.newsDatabaseDao.getNewsFromRoom()
    }

    suspend fun refreshNews() {
        try {
            NewsApi.retrofitService.getLatestNews().enqueue(object : Callback<NewsResponse> {
                override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                    if (response.isSuccessful) {
                        val newsResponse = response.body()
                        if (newsResponse?.results.isNullOrEmpty()) {
//                            _newsList.value = emptyList()
                        } else {
                            var newsListData = newsResponse?.results!!
                            AsyncTask.execute {   database.newsDatabaseDao.getNewsFromRoom()
                            }
                        }
                    } else {
                        Log.e("LatestNewsViewModel", "Error: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    Log.e("LatestNewsViewModel", t.message.toString())
                }
            })
        } catch (e: Exception) {
            Log.e("LatestNewsViewModel", e.message.toString())
        }
    }

}
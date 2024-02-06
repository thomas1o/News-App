package com.example.newsapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.News
import com.example.newsapp.data.NewsResponse
import com.example.newsapp.model.NewsApi
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {

    private val _newsList = MutableLiveData<List<News>>()
    val newsList: LiveData<List<News>>
        get() =_newsList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val _loadingFinished = MutableLiveData<Boolean>()
    val loadingFinished: LiveData<Boolean>
        get() = _loadingFinished

    private var numberOfItems: Int = 0

    init{

        getNews()

        _loadingFinished.value = false

    }

    fun getNews() {
        try {
            NewsApi.retrofitService.getLatestNews().enqueue(object : Callback<NewsResponse> {
                override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                    if (response.isSuccessful) {
                        val newsResponse = response.body()
                        if (newsResponse?.results.isNullOrEmpty()) {
                            _errorMessage.value = "No results found!"
                            _newsList.value = emptyList()
                        } else {
                            _newsList.value = newsResponse?.results!!
                            numberOfItems = newsResponse.totalResults
                        }
                        _loadingFinished.value = true
                    } else {
                        Log.e("NewsViewModel", "Error: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    _errorMessage.value = "Error: ${t.message}"
                    Log.e("NewsViewModel", t.message.toString())
                    _loadingFinished.value = true
                }
            })
        } catch (e: Exception) {
            Log.e("NewsViewModel", e.message.toString())
        }
    }

    fun getNewsUsingKeyword(keyword: String) {
        _loadingFinished.value = false
        try {
            NewsApi.retrofitService.getNewsUsingKeyword(keyword).enqueue(object : Callback<NewsResponse> {
                override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                    if (response.isSuccessful) {
                        val newsResponse = response.body()
                        if (newsResponse?.results.isNullOrEmpty()) {
                            _errorMessage.value = "No results found!"
                            _newsList.value = emptyList()
                        } else {
                            _newsList.value = newsResponse?.results!!
                            numberOfItems = newsResponse.totalResults
                        }
                        _loadingFinished.value = true
                    } else {
                        Log.e("NewsViewModel", "Error: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    _errorMessage.value = "Error: ${t.message}"
                    Log.e("NewsViewModel", t.message.toString())
                    _loadingFinished.value = true
                }
            })
        } catch (e: Exception) {
            Log.e("NewsViewModel", e.message.toString())
        }
    }

}
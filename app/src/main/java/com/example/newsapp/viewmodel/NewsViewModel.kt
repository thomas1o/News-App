package com.example.newsapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.data.News
import com.example.newsapp.data.NewsResponse
import com.example.newsapp.model.NewsApi
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

    val numberOfItems = MutableLiveData<String>()

    private val sampleNewsList: List<News> = listOf(
        News(
            id = "1",
            title = "First News",
            description = "This is the first news item in the list.",
            language = "English"
        ),
        News(
            id = "2",
            title = "Second News",
            description = "This is the second news item in the list.",
            language = "English"
        ),
        News(
            id = "3",
            title = "Third News",
            description = "This is the third news item in the list.",
            language = "English"
        ),
        News(
            id = "4",
            title = "Third News",
            description = "This is the third news item in the list.",
            language = "Spanish"
        ),
        News(
            id = "5",
            title = "Third News",
            description = "This is the third news item in the list.",
            language = "En"
        ),
        News(
            id = "6",
            title = "Third News",
            description = "This is the third news item in the list.",
            language = "E"
        ),
        News(
            id = "7",
            title = "Third News",
            description = "This is the third news item in the list.",
            language = "Eng"
        ),
        News(
            id = "8",
            title = "First News",
            description = "This is the ",
            language = "English"
        ),
        News(
            id = "9",
            title = "First News",
            description = "This is the ",
            language = "English"
        ),
        News(
            id = "10",
            title = "First News",
            description = "This is the ",
            language = "English"
        ),
        News(
            id = "11",
            title = "First News",
            description = "This is the ",
            language = "English"
        ),
        News(
            id = "12",
            title = "First News",
            description = "This is the ",
            language = "English"
        ),
        News(
            id = "13",
            title = "First News",
            description = "This is the ",
            language = "English"
        ),
        News(
            id = "14",
            title = "First News",
            description = "This is the ",
            language = "English"
        ),
        News(
            id = "15",
            title = "First News",
            description = "This is the ",
            language = "English"
        ),
        News(
            id = "16",
            title = "First News",
            description = "This is the ",
            language = "English"
        )
    )

    init{

        getNews()

//        viewModelScope.launch {
//            try {
//                val result = NewsApi.retrofitService.getLatestNews()
//                _newsList.value = result
//            } catch (e: Exception) {
//                Log.e("NewsViewModel", e.message.toString())
//            }
//        }`
//        _newsList.value = emptyList()
//        _newsList.value = sampleNewsList

    }

    private fun getNews() {
        NewsApi.retrofitService.getLatestNews().enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    val newsResponse = response.body()
                    _newsList.value = newsResponse?.results ?: emptyList()
                    numberOfItems.value = "Success: ${newsResponse?.totalResults} news retrieved"
                } else {
                    Log.e("NewsViewModel", "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                _errorMessage.value = "Error: ${t.message}"
                Log.e("NewsViewModel", t.message.toString())
            }
        })
    }

    fun getNewsAndRefreshRecyclerView() {
        _newsList.postValue(sampleNewsList)
    }

}
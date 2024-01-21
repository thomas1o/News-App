package com.example.newsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.data.News

class NewsViewModel : ViewModel() {

    private val _newsList = MutableLiveData<List<News>>()
    val newsList: LiveData<List<News>>
        get() =_newsList

    init{

//        _newsList.value = emptyList()
        _newsList.value = listOf(
            News(
                title = "First News",
                description = "This is the first news item in the list.",
                language = "English"
            ),
            News(
                title = "Second News",
                description = "This is the second news item in the list.",
                language = "English"
            ),
            News(
                title = "Third News",
                description = "This is the third news item in the list.",
                language = "English"
            ),
            News(
                title = "Third News",
                description = "This is the third news item in the list.",
                language = "Spanish"
            ),
            News(
                title = "Third News",
                description = "This is the third news item in the list.",
                language = "En"
            ),
            News(
                title = "Third News",
                description = "This is the third news item in the list.",
                language = "E"
            ),
            News(
                title = "Third News",
                description = "This is the third news item in the list.",
                language = "Eng"
            ),
            News(
                title = "Third News",
                description = "This is the ",
                language = "English"
            )
        )

    }

    fun getNews() {

    }




}
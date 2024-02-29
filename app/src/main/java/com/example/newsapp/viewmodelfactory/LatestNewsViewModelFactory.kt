package com.example.newsapp.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.data.NewsDatabaseDao
import com.example.newsapp.viewmodel.LatestNewsViewModel

//class LatestNewsViewModelFactory(
//    private val dataSource: NewsDatabaseDao, //Note- factory providing it makes it easier to test
//    private val application: Application
//    ): ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(LatestNewsViewModel::class.java)) {
//            return LatestNewsViewModel(dataSource, application) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}
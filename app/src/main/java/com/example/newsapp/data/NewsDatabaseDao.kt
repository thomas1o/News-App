package com.example.newsapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NewsDatabaseDao {

    @Query("Select * from news_table")
    fun getNewsFromRoom(): LiveData<List<News>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewsToRoom(news: List<News>)

    @Query("Delete from news_table")
    fun clear()

}
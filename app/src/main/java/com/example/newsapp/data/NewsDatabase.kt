package com.example.newsapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [News::class], version = 1, exportSchema = false)
abstract class NewsDatabase: RoomDatabase() {
    abstract val newsDatabaseDao: NewsDatabaseDao

    companion object {
        @Volatile
        private lateinit var INSTANCE: NewsDatabase

        fun getDatabase(context: Context): NewsDatabase {
            synchronized(NewsDatabase::class.java){
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext, NewsDatabase::class.java,
                        "news_database"
                    )
                        .fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE
        }
    }
}



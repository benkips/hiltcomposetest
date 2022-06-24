package com.mabnets.hiltcomposetest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mabnets.hiltcomposetest.data.local.Dao.NewsDao
import com.mabnets.hiltcomposetest.data.local.Entity.Newsentity

@Database(entities = [Newsentity::class],version=1)
abstract  class NewsDatabase : RoomDatabase() {

    abstract fun getNewsDao(): NewsDao
}
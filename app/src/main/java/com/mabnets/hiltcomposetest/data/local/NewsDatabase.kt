package com.mabnets.hiltcomposetest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mabnets.hiltcomposetest.data.local.Entity.Mydata


@Database(entities = [Mydata::class],version=1)
abstract  class NewsDatabase : RoomDatabase() {

    abstract fun mydataDao(): MyDataDao
}
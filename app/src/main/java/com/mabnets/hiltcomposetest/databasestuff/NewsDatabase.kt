package com.mabnets.hiltcomposetest.databasestuff

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mabnets.hiltcomposetest.models.Mydata


@Database(entities = [Mydata::class],version=1)
abstract  class NewsDatabase : RoomDatabase() {

    abstract fun mydataDao(): MyDataDao
}
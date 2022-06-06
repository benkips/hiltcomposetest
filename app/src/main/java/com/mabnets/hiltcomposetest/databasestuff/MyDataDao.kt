package com.mabnets.hiltcomposetest.databasestuff

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mabnets.hiltcomposetest.models.Mydata
import kotlinx.coroutines.flow.Flow

@Dao
interface  MyDataDao {
    @Query("SELECT *  FROM  news  WHERE type=:search")
    fun getAllnews(search: String?): Flow<List<Mydata>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllnews(newz:List<Mydata>)

    @Query("DELETE FROM news  WHERE type=:search")
    suspend fun  deleteAllnews(search: String?)
}
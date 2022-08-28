package com.mabnets.hiltcomposetest.data.local.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mabnets.hiltcomposetest.data.local.Entity.Newsentity
import com.mabnets.hiltcomposetest.data.remote.dto.Newsdto
import com.mabnets.hiltcomposetest.domain.model.News
import kotlinx.coroutines.flow.Flow


@Dao
interface  NewsDao  {
    @Query("SELECT *  FROM  news  WHERE type=:search")
    fun getAllnews(search: String?): Flow<List<News>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllnews(news:List<Newsentity>)

    @Query("DELETE FROM news  WHERE type=:search")
    suspend fun  deleteAllnews(search: String?)
}
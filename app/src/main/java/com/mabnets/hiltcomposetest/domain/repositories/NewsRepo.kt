package com.mabnets.hiltcomposetest.domain.repositories

import com.mabnets.hiltcomposetest.data.remote.dto.Newsdto
import kotlinx.coroutines.flow.Flow

interface NewsRepo {

    suspend fun getNews(query: String): Flow<Result<List<Newsdto>>>

}
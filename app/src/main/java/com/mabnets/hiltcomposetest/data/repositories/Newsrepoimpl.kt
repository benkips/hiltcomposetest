package com.mabnets.hiltcomposetest.data.repositories

import com.mabnets.hiltcomposetest.data.remote.dto.Newsdto
import com.mabnets.hiltcomposetest.domain.repositories.NewsRepo
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import javax.inject.Inject

class  Newsrepoimpl  @Inject constructor(private val api:Retrofit): NewsRepo {

    override suspend fun getNews(query: String): Flow<Result<List<Newsdto>>> {
        TODO("Not yet implemented")
    }

}
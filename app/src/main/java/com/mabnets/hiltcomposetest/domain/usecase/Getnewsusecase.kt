package com.mabnets.hiltcomposetest.domain.usecase

import com.mabnets.hiltcomposetest.domain.model.News
import com.mabnets.hiltcomposetest.domain.repositories.NewsRepo
import com.mabnets.hiltcomposetest.data.Result
import com.mabnets.hiltcomposetest.data.remote.dto.tonews
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class Getnewsusecase @Inject constructor(val repository:NewsRepo){

    suspend operator fun invoke(query: String): Flow<Result<List<News>>>  = flow {
        try {
            emit(Result.Loading<List<News>>())
            val News = repository.getNews(query).map { it.tonews() }
            emit(Result.Success<List<News>>(News))
        } catch(e: HttpException) {
            emit(Result.Error<List<News>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Result.Error<List<News>>("Couldn't reach server. Check your internet connection."))
        }
    }
  }


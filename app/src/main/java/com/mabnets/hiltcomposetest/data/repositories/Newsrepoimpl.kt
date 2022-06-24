package com.mabnets.hiltcomposetest.data.repositories

import com.mabnets.hiltcomposetest.data.remote.RemoteApi
import com.mabnets.hiltcomposetest.data.remote.dto.tonews
import com.mabnets.hiltcomposetest.domain.model.News
import com.mabnets.hiltcomposetest.domain.repositories.NewsRepo
import com.mabnets.hiltcomposetest.data.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class Newsrepoimpl @Inject constructor(private val api: RemoteApi) : NewsRepo {

    override fun getNews(query: String): Flow<Result<List<News>>> = flow {
        emit(Result.Loading())
        try {
            val response = api.getnews(query).map { it.tonews() }
            emit(Result.Success(response))
        } catch (e: HttpException) {
            emit(
                Result.Error(
                    message = "Oops, something went wrong",
                    data = null
                )
            )
        } catch (e: IOException) {
            emit(
                Result.Error(
                    message = "Couldn't reach server, check your internet connection",
                    data = null
                )
            )
        }

    }
}
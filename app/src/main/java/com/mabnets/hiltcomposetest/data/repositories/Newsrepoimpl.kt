package com.mabnets.hiltcomposetest.data.repositories


import com.mabnets.hiltcomposetest.data.remote.RemoteApi
import com.mabnets.hiltcomposetest.data.remote.dto.Newsdto
import com.mabnets.hiltcomposetest.domain.repositories.NewsRepo
import javax.inject.Inject

class  Newsrepoimpl  @Inject constructor(private val api: RemoteApi): NewsRepo {

    override suspend fun getNews(query: String): List<Newsdto>  {
         return api.getnews(query)
      }

}
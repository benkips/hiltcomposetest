package com.mabnets.hiltcomposetest.domain.repositories

import com.mabnets.hiltcomposetest.domain.model.News
import kotlinx.coroutines.flow.Flow
import com.mabnets.hiltcomposetest.data.Result
import com.mabnets.hiltcomposetest.data.remote.dto.Newsdto

interface NewsRepo {

     suspend fun getNews(query: String): List<Newsdto>

}
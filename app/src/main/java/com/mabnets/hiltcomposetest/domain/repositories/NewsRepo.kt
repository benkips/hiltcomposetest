package com.mabnets.hiltcomposetest.domain.repositories

import com.mabnets.hiltcomposetest.domain.model.News
import kotlinx.coroutines.flow.Flow
import com.mabnets.hiltcomposetest.data.Result

interface NewsRepo {

     fun getNews(query: String): Flow<Result<List<News>>>

}
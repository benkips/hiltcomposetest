package com.mabnets.hiltcomposetest.domain.usecase

import com.mabnets.hiltcomposetest.domain.model.News
import com.mabnets.hiltcomposetest.domain.repositories.NewsRepo
import com.mabnets.hiltcomposetest.data.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class Getnewusecase @Inject constructor(
    private val repository: NewsRepo
) {
    operator fun invoke(q: String): Flow<Result<List<News>>> {
        return repository.getNews(q)
    }
}


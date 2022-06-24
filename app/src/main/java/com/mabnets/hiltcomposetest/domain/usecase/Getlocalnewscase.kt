package com.mabnets.hiltcomposetest.domain.usecase

import com.mabnets.hiltcomposetest.data.Result
import com.mabnets.hiltcomposetest.data.remote.dto.Newsdto
import com.mabnets.hiltcomposetest.domain.model.News
import com.mabnets.hiltcomposetest.domain.repositories.LocalRepo
import com.mabnets.hiltcomposetest.domain.repositories.NewsRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Getlocalnewscase @Inject constructor(
    private val repository: LocalRepo
) {
    operator fun invoke(q: String): Flow<Result<List<News>>> {
        return repository.getNewsformlocal(q) as Flow<Result<List<News>>>
    }
}
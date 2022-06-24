package com.mabnets.hiltcomposetest.domain.repositories

import com.mabnets.hiltcomposetest.data.Result
import com.mabnets.hiltcomposetest.domain.model.News
import kotlinx.coroutines.flow.Flow

    interface LocalRepo{

        fun getNewsformlocal(query: String) : Flow<Result<out List<News>>>

    }
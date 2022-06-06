package com.mabnets.hiltcomposetest.viewmodel

import com.mabnets.hiltcomposetest.models.Mydata
import com.mabnets.hiltcomposetest.network.Errorcodez

data class NewsState (
    val MydataItems: List<Mydata> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null,
    val errorCode: Errorcodez? = null
)
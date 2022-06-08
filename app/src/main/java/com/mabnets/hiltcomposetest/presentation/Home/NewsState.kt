package com.mabnets.hiltcomposetest.presentation.Home

import com.mabnets.hiltcomposetest.domain.model.News


data class NewsState (
    val NewsItems: List<News> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = "",
)
package com.mabnets.hiltcomposetest.domain.model

data class News(
    val id: Int,
    val links: String,
    val title: String,
    val type: String,
    val description: String,
    val imagelink:String
)

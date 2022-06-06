package com.mabnets.hiltcomposetest.network


enum class Errorcodez(val code: Int) {
    WORD_NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500),
    UNKNOWN(0)
}
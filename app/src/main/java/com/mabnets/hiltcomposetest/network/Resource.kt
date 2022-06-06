package com.mabnets.hiltcomposetest.Network

import com.mabnets.hiltcomposetest.models.Mydata
import com.mabnets.hiltcomposetest.network.Errorcodez

typealias SimpleResource = Resource<Unit>

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>(data: T? = null): Resource<T>(data)
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(message: String, data: T? = null, val errorCode: Errorcodez? = null): Resource<T>(data, message)
}
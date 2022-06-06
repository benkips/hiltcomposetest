package com.mabnets.hiltcomposetest.Utils

import com.mabnets.hiltcomposetest.Network.Resource
import com.mabnets.hiltcomposetest.network.Errorcodez
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import java.io.IOException

inline fun <ResultType, RequestType> NetworkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldfetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()
    val flow = if (shouldfetch(data)) {
        emit(Resource.Loading(data))
        try {
            saveFetchResult(fetch())
            query().map { Resource.Success(it) }
        } catch (e: HttpException) {
            query().map {
                if (e.code() != Errorcodez.WORD_NOT_FOUND.code) {
                    Resource.Error(
                        message = "Oops, something went wrong!",
                        data = it,
                        errorCode = Errorcodez.UNKNOWN
                    )
                }

            }

        } catch (e: IOException) {
            query().map {
                Resource.Error(
                    message = "Couldn't reach server, check your internet connection.",
                    data = it
                )
            }
        } catch (e: Exception) {
            query().map {
                Resource.Error(
                    message = "Oops, something went wrong!",
                    data = it
                )
            }
        }

    }else {
        query().map { Resource.Success(it) }
    }
    emitAll(flow)
}
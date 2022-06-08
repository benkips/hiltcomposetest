package com.mabnets.hiltcomposetest.Utils

import com.mabnets.hiltcomposetest.Network.Resource
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import java.io.IOException

inline  fun <ResultType,RequestType> NetworkBoundResource (
    crossinline query: ()-> Flow<ResultType>,
    crossinline fetch: suspend ()-> RequestType,
    crossinline saveFetchResult: suspend (RequestType)->Unit,
    crossinline shouldfetch: (ResultType)-> Boolean = {true}
)= flow {
    val data=query().first()

    val flow=if(shouldfetch(data)){
        emit(Resource.Loading(data))
        try {
            saveFetchResult(fetch())
            query().map { Resource.Success(it) }
        }catch (throwable:Throwable){
            query().map { Resource.Error(throwable,it) }

        }
    }else{
        query().map { Resource.Success(it) }
    }
    emitAll(flow)
}
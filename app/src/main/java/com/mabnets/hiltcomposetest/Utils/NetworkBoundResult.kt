package com.mabnets.hiltcomposetest.Utils
import com.mabnets.hiltcomposetest.data.Result
import com.mabnets.hiltcomposetest.domain.model.News
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import java.io.IOException

inline  fun <ResultType,RequestType> NetworkBoundResult (
    crossinline query: ()-> Flow<ResultType>,
    crossinline fetch: suspend ()-> RequestType,
    crossinline saveFetchResult: suspend (RequestType)->Unit,
    crossinline shouldfetch: (ResultType)-> Boolean = {true}
)= flow {
    val data=query().first()

    val flow=if(shouldfetch(data)){
        emit(Result.Loading(data))
        try {
            saveFetchResult(fetch())
            query().map { Result.Success(it) }
        }catch (throwable: HttpException){
            query().map {
                Result.Error(
                    message = "Oops, something went wrong",
                    data = null
                )
            }

        }catch (throwable: IOException) {
            query().map {
                Result.Error(
                    message = "Couldn't reach server, check your internet connection",
                    data = null
                )
            }

        }catch (throwable:Throwable) {
           val msg=throwable.localizedMessage

            query().map {
                Result.Error(
                    message = "${msg}",
                    data = null
                )
            }
        }
    }else{
        query().map { Result.Success(it) }
    }
    emitAll(flow)
}

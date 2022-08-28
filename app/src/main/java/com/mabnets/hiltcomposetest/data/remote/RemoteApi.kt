package com.mabnets.hiltcomposetest.data.remote

import com.mabnets.hiltcomposetest.data.remote.dto.Newsdto
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteApi{

    @GET("allnews")
    suspend  fun getnews(@Query("data")dataz:String?): List<Newsdto>

}
package com.mabnets.hiltcomposetest.Repo


import androidx.room.withTransaction
import com.mabnets.hiltcomposetest.Network.ApiInterface
import com.mabnets.hiltcomposetest.Utils.NetworkBoundResource
import com.mabnets.hiltcomposetest.databasestuff.NewsDatabase
import kotlinx.coroutines.delay
import javax.inject.Inject

class Repostuff @Inject constructor(
    private val apiInterface: ApiInterface,
    private  val db: NewsDatabase
){
    private  val mydataaDao=db.mydataDao()

    fun getnewsresults(newstation: String)= NetworkBoundResource(
        query = {
            mydataaDao.getAllnews(newstation)
        },
        fetch = {
            apiInterface.getnews(newstation)
        },
        saveFetchResult = {news->
            db.withTransaction {
                mydataaDao.deleteAllnews(newstation)
                mydataaDao.insertAllnews(news)
            }

        }
    )
}


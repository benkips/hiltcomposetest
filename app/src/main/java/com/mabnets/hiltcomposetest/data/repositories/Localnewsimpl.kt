package com.mabnets.hiltcomposetest.data.repositories

import androidx.room.withTransaction
import com.mabnets.hiltcomposetest.Utils.NetworkBoundResult
import com.mabnets.hiltcomposetest.data.local.NewsDatabase
import com.mabnets.hiltcomposetest.data.remote.RemoteApi
import com.mabnets.hiltcomposetest.data.remote.dto.tonewsentity
import com.mabnets.hiltcomposetest.domain.repositories.LocalRepo
import javax.inject.Inject

class Localnewsimpl  @Inject constructor(
    private val api: RemoteApi,
    private  val db: NewsDatabase
):LocalRepo{
    private  val newsDao=db.getNewsDao()
    override fun getNewsformlocal(q: String)=NetworkBoundResult(
    query = {
        newsDao.getAllnews(q)
    },
    fetch = {
        api.getnews(q)
    },
    saveFetchResult = {news->
        db.withTransaction {
            newsDao.deleteAllnews(q)
            newsDao.insertAllnews(news.map { it.tonewsentity() })
        }

    }

    )


}
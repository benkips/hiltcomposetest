package com.mabnets.hiltcomposetest.Repo


import com.mabnets.hiltcomposetest.Network.ApiInterface
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/*class Repostuff @Inject constructor(
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
}*/
class Repostuff @Inject constructor (private val apiInterface: ApiInterface){

    suspend fun getSearchresults(query: String) = flow {
        apiInterface.getnews(query)
    }

}

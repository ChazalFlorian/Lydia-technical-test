package fr.florian.lydia.technicaltest.data.repositories

import android.util.Log
import fr.florian.lydia.technicaltest.data.local.daos.UserDao
import fr.florian.lydia.technicaltest.data.models.Result
import fr.florian.lydia.technicaltest.data.models.User
import fr.florian.lydia.technicaltest.data.remote.UserApi
import javax.inject.Inject

class UserRepository : BaseRepository() {

    @Inject
    lateinit var userRemoteApi: UserApi

    @Inject
    lateinit var userDao: UserDao

    suspend fun loadUsersBatch(page: Int = 1, results: Int = 10, hasInternetConnection: Boolean): List<User> {
        if (hasInternetConnection) {
            val res: List<User> = retrieveRemoteUsersBatch(page, results).results
            saveLocalUsersBatch(res)
            return res
        } else {
            return retrieveLocalUsersBatch(page, results)
        }
    }


    private fun retrieveLocalUsersBatch(page: Int, results: Int): List<User> {
        val offset = results * page
        return userDao.getAllByBatch(offset, results)
    }

    private suspend fun retrieveRemoteUsersBatch(page: Int, results: Int): Result<User> {
        return userRemoteApi.getUsersBatch(results, page)
    }

    private fun saveLocalUsersBatch(result: List<User>) {
        userDao.insertAllOrders(result)
    }
}
package fr.florian.lydia.technicaltest.data.repositories

import fr.florian.lydia.technicaltest.data.models.Result
import fr.florian.lydia.technicaltest.data.models.User
import fr.florian.lydia.technicaltest.data.remote.routes.UserApi
import javax.inject.Inject

class UserRepository: BaseRepository() {

    @Inject
    lateinit var userRemoteApi: UserApi

    suspend fun loadUsersBatch(page:Int = 1, results:Int = 10): Result<User> = userRemoteApi.getUsersBatch(results, page)
}
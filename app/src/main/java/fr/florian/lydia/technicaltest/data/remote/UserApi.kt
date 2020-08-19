package fr.florian.lydia.technicaltest.data.remote

import fr.florian.lydia.technicaltest.data.models.Result
import fr.florian.lydia.technicaltest.data.models.User
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET(" ")
    suspend fun getUsersBatch(
        @Query("results") results: Int,
        @Query("page") page: Int,
        @Query("seed") seed: String = "lydia"
    ): Result<User>
}
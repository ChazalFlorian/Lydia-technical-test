package fr.florian.lydia.technicaltest.injection.modules

import dagger.Module
import dagger.Provides
import dagger.Reusable
import fr.florian.lydia.technicaltest.data.remote.routes.UserApi
import fr.florian.lydia.technicaltest.util.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@Suppress("unused")
object NetworkModule {

    /**
     * Provides the User service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the User service implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideUserApi(retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
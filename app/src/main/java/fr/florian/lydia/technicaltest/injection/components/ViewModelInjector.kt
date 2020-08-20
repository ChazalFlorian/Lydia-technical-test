package fr.florian.lydia.technicaltest.injection.components

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import fr.florian.lydia.technicaltest.LydiaApplication
import fr.florian.lydia.technicaltest.data.repositories.UserRepository
import fr.florian.lydia.technicaltest.injection.modules.DatabaseModule
import fr.florian.lydia.technicaltest.injection.modules.NetworkModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidInjectionModule::class), (NetworkModule::class), (DatabaseModule::class)])
interface ViewModelInjector: AndroidInjector<LydiaApplication> {
    /**
     * Injects required dependencies into the specified UserRepository.
     * @param userRepository UserRepository in which to inject the dependencies
     */
    fun inject(userRepository: UserRepository)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun networkModule(networkModule: NetworkModule): Builder

        fun databaseModule(databaseModule: DatabaseModule): Builder

        fun build(): ViewModelInjector
    }
}
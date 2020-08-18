package fr.florian.lydia.technicaltest.injection.components

import dagger.Component
import fr.florian.lydia.technicaltest.data.repositories.UserRepository
import fr.florian.lydia.technicaltest.injection.modules.NetworkModule
import fr.florian.lydia.technicaltest.ui.viewmodels.SplashViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified PostListViewModel.
     * @param userRepository UserRepository in which to inject the dependencies
     */
    fun inject(userRepository: UserRepository)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}
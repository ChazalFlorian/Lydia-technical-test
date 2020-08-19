package fr.florian.lydia.technicaltest.data.repositories

import fr.florian.lydia.technicaltest.injection.components.DaggerViewModelInjector
import fr.florian.lydia.technicaltest.injection.components.ViewModelInjector
import fr.florian.lydia.technicaltest.injection.modules.NetworkModule


abstract class BaseRepository {
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is UserRepository -> injector.inject(this)
        }
    }
}
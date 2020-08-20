package fr.florian.lydia.technicaltest.data.repositories

import fr.florian.lydia.technicaltest.LydiaApplication
import fr.florian.lydia.technicaltest.injection.components.ViewModelInjector


abstract class BaseRepository {

    private val injector: ViewModelInjector = LydiaApplication.instance.injector

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
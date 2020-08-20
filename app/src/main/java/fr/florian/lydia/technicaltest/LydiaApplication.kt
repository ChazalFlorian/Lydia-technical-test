package fr.florian.lydia.technicaltest

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import fr.florian.lydia.technicaltest.data.repositories.BaseRepository
import fr.florian.lydia.technicaltest.injection.components.DaggerViewModelInjector
import fr.florian.lydia.technicaltest.injection.components.ViewModelInjector
import fr.florian.lydia.technicaltest.injection.modules.DatabaseModule
import fr.florian.lydia.technicaltest.injection.modules.NetworkModule
import javax.inject.Inject

class LydiaApplication : DaggerApplication() {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<BaseRepository>

    lateinit var injector: ViewModelInjector

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        injector = DaggerViewModelInjector
            .builder()
            .application(this)
            .networkModule(NetworkModule)
            .databaseModule(DatabaseModule)
            .build()
        return injector
    }

    companion object {
        lateinit var instance: LydiaApplication
    }
}
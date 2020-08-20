package fr.florian.lydia.technicaltest.injection.modules

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.Reusable
import fr.florian.lydia.technicaltest.data.local.daos.UserDao
import fr.florian.lydia.technicaltest.data.local.database.LydiaDatabase
import fr.florian.lydia.technicaltest.util.DATABASE_NAME
import javax.inject.Singleton

@Module
@Suppress("unused")
object DatabaseModule {

    @Provides
    internal fun provideUserDao(db: LydiaDatabase): UserDao = db.userDao()

    @Provides
    @Singleton
    internal fun provideLydiaDatabase(application: Application): LydiaDatabase {
        return Room.databaseBuilder(
            application,
            LydiaDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }
}
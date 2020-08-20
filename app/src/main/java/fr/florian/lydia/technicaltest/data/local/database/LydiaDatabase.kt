package fr.florian.lydia.technicaltest.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fr.florian.lydia.technicaltest.data.local.UserTypeConverter
import fr.florian.lydia.technicaltest.data.local.daos.UserDao
import fr.florian.lydia.technicaltest.data.models.User

@Database(entities = [User::class], version = 1, exportSchema = false)
@TypeConverters(UserTypeConverter::class)
abstract class LydiaDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
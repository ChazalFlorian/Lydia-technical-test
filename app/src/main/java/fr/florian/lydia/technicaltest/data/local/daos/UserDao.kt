package fr.florian.lydia.technicaltest.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import fr.florian.lydia.technicaltest.data.models.User

@Dao
interface UserDao {
    @get:Query("SELECT * FROM user")
    val all: List<User>

    @Insert
    fun insertAll(vararg posts: User)
}
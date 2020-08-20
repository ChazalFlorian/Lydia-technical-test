package fr.florian.lydia.technicaltest.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.florian.lydia.technicaltest.data.models.User


@Dao
interface UserDao {

    @Query("SELECT * FROM user LIMIT :results OFFSET :offset")
    fun getAllByBatch(offset: Int, results: Int): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllOrders(order: List<User>)
}
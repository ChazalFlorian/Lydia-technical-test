package fr.florian.lydia.technicaltest.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import fr.florian.lydia.technicaltest.data.models.User

class UserTypeConverter {

    @TypeConverter
    fun nameToString(name: User.Name): String = Gson().toJson(name)

    @TypeConverter
    fun stringToName(string: String): User.Name = Gson().fromJson(string, User.Name::class.java)

    @TypeConverter
    fun locationToString(location: User.Location): String = Gson().toJson(location)

    @TypeConverter
    fun stringToLocation(string: String): User.Location =
        Gson().fromJson(string, User.Location::class.java)

    @TypeConverter
    fun loginToString(login: User.Login): String = Gson().toJson(login)

    @TypeConverter
    fun stringToLogin(string: String): User.Login = Gson().fromJson(string, User.Login::class.java)

    @TypeConverter
    fun idToString(id: User.Id): String = Gson().toJson(id)

    @TypeConverter
    fun stringToId(string: String): User.Id = Gson().fromJson(string, User.Id::class.java)

    @TypeConverter
    fun pictureToString(picture: User.Picture): String = Gson().toJson(picture)

    @TypeConverter
    fun stringToPicture(string: String): User.Picture =
        Gson().fromJson(string, User.Picture::class.java)
}
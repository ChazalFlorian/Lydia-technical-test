package fr.florian.lydia.technicaltest.data.models

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.*
import kotlinx.android.parcel.Parcelize
import java.lang.reflect.Type

@Parcelize
@Entity(primaryKeys = ["email"])
data class User(
    val gender: String,
    val name: Name,
    val location: Location,
    val email: String,
    val login: Login,
    val registered: Int,
    val dob: Int,
    val phone: String,
    val cell: String,
    val id: Id,
    val picture: Picture,
    val nat: String
) : Parcelable {

    @Parcelize
    @Entity
    data class Name(
        val title: String,
        val first: String,
        val last: String
    ) : Parcelable

    @Parcelize
    @Entity
    data class Location(
        val street: String,
        val city: String,
        val state: String,
        var postcode: String
    ) : Parcelable

    @Parcelize
    @Entity
    data class Login(
        val username: String,
        val password: String,
        val salt: String,
        val md5: String,
        val sha1: String,
        val sha256: String
    ) : Parcelable

    @Parcelize
    @Entity
    data class Id(
        val name: String,
        val value: String?
    ) : Parcelable

    @Parcelize
    @Entity
    data class Picture(
        val large: String,
        val medium: String,
        val thumbnail: String
    ) : Parcelable

    class postCodeDeserializer() : JsonDeserializer<User> {
        override fun deserialize(
            json: JsonElement?,
            typeOfT: Type?,
            context: JsonDeserializationContext?
        ): User {
            val user: User = Gson().fromJson(json, User::class.java)
            val jsonObject: JsonObject? = json?.asJsonObject
            jsonObject?.let { it ->
                if (it.has("postcode")) {
                    val elem: JsonElement = it.get("postcode")
                    user.location.postcode = elem.asJsonObject.asString
                }
            }
            return user
        }

    }
}
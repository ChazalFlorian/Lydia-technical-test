package fr.florian.lydia.technicaltest.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
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
    data class Name(
        val title: String,
        val first: String,
        val last: String
    ) : Parcelable

    @Parcelize
    data class Location(
        val street: String,
        val city: String,
        val state: String,
        val postcode: Int
    ) : Parcelable

    @Parcelize
    data class Login(
        val username: String,
        val password: String,
        val salt: String,
        val md5: String,
        val sha1: String,
        val sha256: String
    ) : Parcelable

    @Parcelize
    data class Id(
        val name: String,
        val value: String?
    ) : Parcelable

    @Parcelize
    data class Picture(
        val large: String,
        val medium: String,
        val thumbnail: String
    ) : Parcelable


}
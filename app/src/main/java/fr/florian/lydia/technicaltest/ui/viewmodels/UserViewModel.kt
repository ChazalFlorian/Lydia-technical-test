package fr.florian.lydia.technicaltest.ui.viewmodels

import android.view.View
import androidx.navigation.findNavController
import fr.florian.lydia.technicaltest.data.models.User
import fr.florian.lydia.technicaltest.ui.views.UserListFragmentDirections

class UserViewModel(private val user: User) : BaseViewModel() {

    fun getLastName(): String {
        return user.name.last
    }

    fun getFirstName(): String {
        return user.name.first
    }

    fun getEmail(): String {
        return user.email
    }

    fun openDetails(v: View) {
        val action = UserListFragmentDirections
            .actionUserListFragmentToUserDetailFragment(user)
        v.findNavController().navigate(action)
    }

    fun getFullName(): String {
        return user.name.title + " " +
                user.name.first + " " +
                user.name.last;
    }

    fun getLocationFirstPart():String {
        return user.location.street + " " + user.location.city;
    }

    fun getLocationLastPart() :String {
        return user.location.state + " " + user.location.postcode;
    }

    fun getNationality():String {
        return user.nat
    }

    fun getLogin() : String {
        return user.login.username
    }

    fun getPassword() : String {
        return user.login.password
    }

    fun getSalt() : String {
        return user.login.salt
    }

    fun getMD5() : String {
        return user.login.md5
    }

    fun getSHA1() : String {
        return user.login.sha1
    }

    fun getSHA256() : String {
        return user.login.sha256
    }

    fun getRegistered() : String {
        return user.registered.toString()
    }

    fun getDOB() : String {
        return user.dob.toString()
    }

    fun getPhone() : String {
        return user.phone
    }

    fun getCell() : String {
        return user.cell
    }

    fun getIdName() : String {
        return user.id.name
    }

    fun getIdValue() : String {
        return user.id.value.toString()
    }

    fun getLargeImage() : String {
        //TODO implememt w/ Glide
        return user.picture.large
    }

    fun getMediumImage() : String {
        //TODO implememt w/ Glide
        return user.picture.medium
    }

    fun getThumbnailImage() : String {
        //TODO implememt w/ Glide
        return user.picture.thumbnail
    }
}
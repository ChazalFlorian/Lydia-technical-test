package fr.florian.lydia.technicaltest.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import fr.florian.lydia.technicaltest.data.models.Result
import fr.florian.lydia.technicaltest.data.models.User
import fr.florian.lydia.technicaltest.data.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserListViewModel : BaseViewModel() {

    private val userRepository: UserRepository = UserRepository()

    var users: MutableLiveData<List<User>> = MutableLiveData()
    private var page: Int = 1
    private var request: Int = 10

    init {
        retrieveBatch()
    }

    fun retrieveBatch(reset: Boolean = false) {
        if (reset) {
            page = 1
            request = 10
        }
        viewModelScope.launch {
            val retrievedData = userRepository.loadUsersBatch(page, request)
            page += 1
            request += 10
            users.postValue(retrievedData.results)
        }
    }
}
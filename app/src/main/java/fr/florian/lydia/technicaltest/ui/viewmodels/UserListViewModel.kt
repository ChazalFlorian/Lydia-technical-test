package fr.florian.lydia.technicaltest.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import fr.florian.lydia.technicaltest.data.models.Result
import fr.florian.lydia.technicaltest.data.models.User
import fr.florian.lydia.technicaltest.data.repositories.UserRepository
import kotlinx.coroutines.Dispatchers

class UserListViewModel : BaseViewModel() {

    private val userRepository: UserRepository = UserRepository()

    var users: LiveData<List<User>> = MutableLiveData()
    private var page: Int = 1
    private var request: Int = 10

    init {
        retrieveBatch()
    }

    fun retrieveBatch() {
        users = liveData(Dispatchers.IO) {
            val retrievedData = userRepository.loadUsersBatch(page, request)
            page += 1
            request += 10
            emit(retrievedData.results)
        } as MutableLiveData<List<User>>
    }
}
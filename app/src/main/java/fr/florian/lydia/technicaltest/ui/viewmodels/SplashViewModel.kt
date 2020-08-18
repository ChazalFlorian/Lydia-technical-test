package fr.florian.lydia.technicaltest.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import fr.florian.lydia.technicaltest.data.models.Result
import fr.florian.lydia.technicaltest.data.models.User
import fr.florian.lydia.technicaltest.data.repositories.UserRepository
import kotlinx.coroutines.Dispatchers

class SplashViewModel: BaseViewModel() {

    private val userRepository: UserRepository = UserRepository()

    val data: LiveData<List<User>> = liveData(Dispatchers.IO) {
        val retrievedData = userRepository.loadUsersBatch()
        emit(retrievedData.results)
    }
}
package fr.florian.lydia.technicaltest.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import fr.florian.lydia.technicaltest.R
import fr.florian.lydia.technicaltest.data.models.User
import fr.florian.lydia.technicaltest.ui.viewmodels.SplashViewModel
import java.io.Console

class SplashActivity : AppCompatActivity() {

    lateinit var userViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }
}
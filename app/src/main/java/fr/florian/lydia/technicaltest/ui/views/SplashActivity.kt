package fr.florian.lydia.technicaltest.ui.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.florian.lydia.technicaltest.R
import fr.florian.lydia.technicaltest.ui.viewmodels.UserListViewModel

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, NavHostActivity::class.java))
        finish()
    }
}
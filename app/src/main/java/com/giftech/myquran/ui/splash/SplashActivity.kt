package com.giftech.myquran.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.giftech.myquran.databinding.ActivitySplashBinding
import com.giftech.myquran.ui.home.HomeActivity
import com.giftech.myquran.viewmodel.ViewModelFactory

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val factory = ViewModelFactory.getInstance(this)
        val viewmodel = ViewModelProvider(this,factory)[SplashViewModel::class.java]

        binding.btnGetStarted.setOnClickListener {
            if(viewmodel.isFirstLaunch()){
                viewmodel.setLastReadFirst()
                viewmodel.setSurahFirst()
                viewmodel.setFirstLaunch()
            }
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
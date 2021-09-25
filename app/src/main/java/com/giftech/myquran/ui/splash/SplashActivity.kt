package com.giftech.myquran.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.giftech.myquran.data.source.local.entity.LastReadAyatEntity
import com.giftech.myquran.data.source.local.preferences.Preferences
import com.giftech.myquran.databinding.ActivitySplashBinding
import com.giftech.myquran.ui.home.HomeActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding.btnGetStarted.setOnClickListener {
            val preferences = Preferences(this)
            if(preferences.getIfFirstLaunch()){
                val ayat = LastReadAyatEntity()
                ayat.nomorAyat = 1
                ayat.nomorSurah = 1
                ayat.namaSurah = "Al-Fatihah"
                preferences.setAyat(ayat)
                preferences.setFirstLaunch()
            }

            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
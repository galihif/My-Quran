package com.giftech.myquran.ui.surah

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.giftech.myquran.databinding.ActivitySurahBinding

class SurahActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySurahBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySurahBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
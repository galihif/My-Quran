package com.giftech.myquran.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.giftech.myquran.databinding.ActivityHomeBinding
import com.giftech.myquran.viewmodel.ViewModelFactory

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        val viewmodel = ViewModelProvider(this,factory)[HomeViewModel::class.java]
        val adapter = SurahAdapter()

        viewmodel.getAllSurah().observe(this, {list ->
            adapter.setList(list)
        })

        with(binding.rvSurah){
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }

    }
}
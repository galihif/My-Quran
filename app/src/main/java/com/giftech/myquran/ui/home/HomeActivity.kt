package com.giftech.myquran.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
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

        binding.rvSurah.visibility = View.GONE
        binding.loading.visibility = View.VISIBLE
        viewmodel.getAllSurah().observe(this, {list ->
            adapter.setList(list)
            binding.loading.visibility = View.GONE
            binding.rvSurah.visibility = View.VISIBLE
            Toast.makeText(this, list.size.toString(), Toast.LENGTH_LONG).show()
        })

        with(binding.rvSurah){
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }

    }
}
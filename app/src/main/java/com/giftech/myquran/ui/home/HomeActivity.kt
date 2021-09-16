package com.giftech.myquran.ui.home

import android.os.Bundle
import android.view.View
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

        setLoading(true)
        viewmodel.getAllSurah().observe(this, {list ->
            adapter.setList(list)
            setLoading(false)
        })

        with(binding.rvSurah){
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }
    }

    fun setLoading(isLoading:Boolean){
        if(isLoading){
            binding.rvSurah.visibility = View.GONE
            binding.loading.visibility = View.VISIBLE
        } else{
            binding.loading.visibility = View.GONE
            binding.rvSurah.visibility = View.VISIBLE
        }
    }
}
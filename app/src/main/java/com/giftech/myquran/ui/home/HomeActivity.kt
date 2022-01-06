package com.giftech.myquran.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.giftech.myquran.R
import com.giftech.myquran.data.Resource
import com.giftech.myquran.data.source.local.entity.LastReadAyatEntity
import com.giftech.myquran.data.source.local.entity.SurahEntity
import com.giftech.myquran.databinding.ActivityHomeBinding
import com.giftech.myquran.ui.surah.SurahActivity
import com.giftech.myquran.viewmodel.ViewModelFactory

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var lastReadAyat:LastReadAyatEntity
    private lateinit var surah:SurahEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        val viewmodel = ViewModelProvider(this,factory)[HomeViewModel::class.java]
        val adapter = SurahAdapter()

        setLoading(true)
        viewmodel.getAllSurah().observe(this, {listSurah ->
            if (listSurah != null){
                when(listSurah){
                    is Resource.Loading -> setLoading(true)
                    is Resource.Success -> {
                        setLoading(false)
                        adapter.setList(listSurah.data)
                    }
                    is Resource.Error -> {
                        setLoading(false)
                    }
                }
            }
        })

        viewmodel.getLastRead().observe(this,{ayat ->
            lastReadAyat = ayat
            binding.tvSurahLastread.text = ayat.namaSurah
            binding.tvAyatLastread.text = getString(R.string.last_read_ayat,ayat.nomorAyat)
            adapter.setLastRead(ayat)
        })

        viewmodel.getLastSurah().observe(this, {surahRes->
            this.surah = surahRes
        })

        with(binding.rvSurah){
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }

        binding.btnLastRead.setOnClickListener {
            val intent = Intent(this, SurahActivity::class.java)
            intent.putExtra(SurahActivity.EXTRA_SURAH, surah)
            intent.putExtra(SurahActivity.EXTRA_LASTREAD, lastReadAyat)
            startActivity(intent)
        }

    }

    private fun setLoading(isLoading:Boolean){
        if(isLoading){
            binding.rvSurah.visibility = View.GONE
            binding.loading.visibility = View.VISIBLE
        } else{
            binding.loading.visibility = View.GONE
            binding.rvSurah.visibility = View.VISIBLE
        }
    }
}
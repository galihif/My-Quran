package com.giftech.myquran.ui.surah

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.giftech.myquran.data.source.local.entity.SurahEntity
import com.giftech.myquran.databinding.ActivitySurahBinding
import com.giftech.myquran.viewmodel.ViewModelFactory

class SurahActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_SURAH = "1"
    }

    private lateinit var binding:ActivitySurahBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySurahBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        val viewmodel = ViewModelProvider(this,factory)[SurahViewModel::class.java]
        val adapter = AyatAdapter()

        val extras = intent.extras
        if(extras!=null){
            val surah = extras.getParcelable<SurahEntity>(EXTRA_SURAH)

            Log.d("galih", surah.toString())
            populateView(surah!!)

            setLoading(true)
            viewmodel.getAyatByNomorSurah(surah.nomor).observe(this,{ res ->
                adapter.setList(res)
                setLoading(false)
            })

            with(binding.rvAyat){
                this.layoutManager = LinearLayoutManager(context)
                this.adapter = adapter
            }
        }

    }

    @SuppressLint("SetTextI18n")
    private fun populateView(surah:SurahEntity){
        with(binding.surahDetail){
            tvSurahName.text = surah.nama
            tvSurahArti.text = surah.arti
            tvSurahTypeAyat.text = "${surah.type} - ${surah.ayat} AYAT"
        }
        with(binding){
            tvSurahName.text = surah.nama
            tvSurahName.setOnClickListener {
                onBackPressed()
            }
        }
    }

    private fun setLoading(isLoading:Boolean){
        if(isLoading){
            binding.rvAyat.visibility = View.GONE
            binding.loading.visibility = View.VISIBLE
        } else{
            binding.loading.visibility = View.GONE
            binding.rvAyat.visibility = View.VISIBLE
        }
    }
}
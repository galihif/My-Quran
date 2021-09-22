package com.giftech.myquran.ui.surah

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
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
            val surahNumber = extras.getInt(EXTRA_SURAH,0)

            setLoading(true)
            viewmodel.getAyatByNomorSurah(surahNumber).observe(this,{res ->
                adapter.setList(res)
                setLoading(false)
            })
        }

    }

    fun setLoading(isLoading:Boolean){
        if(isLoading){
            binding.rvAyat.visibility = View.GONE
            binding.loading.visibility = View.VISIBLE
        } else{
            binding.loading.visibility = View.GONE
            binding.rvAyat.visibility = View.VISIBLE
        }
    }
}
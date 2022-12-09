package com.giftech.myquran.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.giftech.myquran.databinding.ActivitySearchBinding
import com.giftech.myquran.ui.home.SurahAdapter
import com.giftech.myquran.viewmodel.ViewModelFactory

class SearchActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        val viewmodel = ViewModelProvider(this,factory)[SearchViewModel::class.java]
        val adapter = SurahAdapter()

        binding.etSearch.requestFocus()

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
//                viewmodel.getSurahByName(s.toString()).observe(this@SearchActivity, {res ->
//                    adapter.setList(res)
//                })
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        viewmodel.getLastRead().observe(this,{ayat ->
            adapter.setLastRead(ayat)
        })

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        with(binding.rvSurah){
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }
    }

}
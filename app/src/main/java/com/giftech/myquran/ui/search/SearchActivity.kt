package com.giftech.myquran.ui.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.giftech.myquran.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etSearch.requestFocus()
//        val imm: InputMethodManager =
    //            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        imm.showSoftInput(binding.etSearch, InputMethodManager.SHOW_IMPLICIT)
    }
}
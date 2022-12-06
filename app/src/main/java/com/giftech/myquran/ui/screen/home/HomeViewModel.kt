package com.giftech.myquran.ui.screen.home

import androidx.lifecycle.ViewModel
import com.giftech.myquran.data.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val repository: MainRepository
) : ViewModel() {
    fun test() = repository.test()
}
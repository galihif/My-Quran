package com.giftech.myquran.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giftech.myquran.data.MainRepository
import com.giftech.myquran.data.Resource
import com.giftech.myquran.data.model.LastRead
import com.giftech.myquran.data.model.Surah
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val _listSurah:MutableStateFlow<Resource<List<Surah>>> = MutableStateFlow(Resource.Loading())
    val listSurah:StateFlow<Resource<List<Surah>>> = _listSurah

    private fun getListSurah(){
        viewModelScope.launch {
            repository.getListSurah().collect{
                _listSurah.value = it
            }
        }
    }

    private val _lastRead:MutableStateFlow<LastRead> = MutableStateFlow(LastRead())
    val lastRead:StateFlow<LastRead> = _lastRead

    fun getLastRead(){
        viewModelScope.launch {
            repository.getLastRead().collect{
                _lastRead.value = it
            }
        }
    }
    init {
        getListSurah()
        getLastRead()
    }
}
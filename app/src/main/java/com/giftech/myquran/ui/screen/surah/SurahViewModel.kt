package com.giftech.myquran.ui.screen.surah

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giftech.myquran.data.MainRepository
import com.giftech.myquran.data.Resource
import com.giftech.myquran.data.model.LastRead
import com.giftech.myquran.data.model.Surah
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SurahViewModel
@Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private var _nomorSurah = MutableStateFlow(1)

    fun setNomorSurah(nomor:Int){
        _nomorSurah.value = nomor
        getDetailSurah()
    }

    private val _surah = MutableStateFlow<Resource<Surah>>(Resource.Loading())
    val surah:StateFlow<Resource<Surah>> = _surah

    private fun getDetailSurah(){
        viewModelScope.launch {
            repository.getDetailSurah(_nomorSurah.value)
                .catch {
                    _surah.value = Resource.Error(it.message.toString())
                }.collect{
                    _surah.value = Resource.Success(it)
                }
        }
    }

    private val _namaSurah = MutableStateFlow("")
    val namaSurah:StateFlow<String> = _namaSurah

    fun setNamaSurah(nama:String){
        _namaSurah.value = nama
    }

    private val _lastRead:MutableStateFlow<LastRead> = MutableStateFlow(LastRead())
    val lastRead:StateFlow<LastRead> = _lastRead

    private fun getLastRead(){
        viewModelScope.launch {
            repository.getLastRead().collect{
                _lastRead.value = it
            }
        }
    }

    fun setLastRead(lastRead: LastRead){
        repository.setLastRead(lastRead)
        getLastRead()
    }

    init {
        getLastRead()
    }



}
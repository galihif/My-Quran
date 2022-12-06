package com.giftech.myquran.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giftech.myquran.data.MainRepository
import com.giftech.myquran.data.Resource
import com.giftech.myquran.data.source.local.entity.SurahEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val repository: MainRepository
) : ViewModel() {
    fun test() = repository.test()

    private val _listSurah:MutableStateFlow<Resource<List<SurahEntity>>> =MutableStateFlow(Resource.Loading())
    val listSurah:StateFlow<Resource<List<SurahEntity>>> = _listSurah

    private fun getListSurah(){
        viewModelScope.launch {
            repository.getListSurah()
                .catch {
                    _listSurah.value = Resource.Error(it.message.toString())
                }.collect{
                    _listSurah.value = Resource.Success(it)
                }
        }
    }

    init {
        getListSurah()
    }
}
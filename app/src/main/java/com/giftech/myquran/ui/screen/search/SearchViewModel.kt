package com.giftech.myquran.ui.screen.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giftech.myquran.data.MainRepository
import com.giftech.myquran.data.model.Surah
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel
@Inject constructor(
    private val repository: MainRepository
) : ViewModel() {
    private val _keyword = mutableStateOf("")
    val keyword: State<String> = _keyword

    fun setKeyword(k:String){
        _keyword.value = k
        if (k.isNotEmpty() && k.isNotBlank()){
            getListSurah()
        }else{
            _listSurah.value = emptyList()
        }
    }

    private val _listSurah: MutableStateFlow<List<Surah>> = MutableStateFlow(emptyList())
    val listSurah: StateFlow<List<Surah>> = _listSurah

    private fun getListSurah(){
        viewModelScope.launch {
            repository.getSearchResult(_keyword.value).collect{
                _listSurah.value = it
            }
        }
    }
}
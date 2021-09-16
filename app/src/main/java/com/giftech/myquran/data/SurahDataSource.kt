package com.giftech.myquran.data

import androidx.lifecycle.LiveData
import com.giftech.myquran.data.source.local.entity.SurahEntity

interface SurahDataSource {

    fun getAllSurah():LiveData<List<SurahEntity>>

}
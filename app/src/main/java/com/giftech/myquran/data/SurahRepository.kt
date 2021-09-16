package com.giftech.myquran.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.giftech.myquran.data.source.local.entity.SurahEntity
import com.giftech.myquran.data.source.remote.RemoteDataSource

class SurahRepository private constructor(
    private val remoteDataSource: RemoteDataSource
):SurahDataSource{

    companion object {
        @Volatile
        private var instance: SurahRepository? = null
        fun getInstance(remoteData: RemoteDataSource): SurahRepository =
            instance ?: synchronized(this) {
                instance ?: SurahRepository(remoteData).apply { instance = this }
            }
    }

    override fun getAllSurah(): LiveData<List<SurahEntity>> {
        val listSurah = MutableLiveData<List<SurahEntity>>()
        val listSurahRes = ArrayList<SurahEntity>()
        remoteDataSource.getAllSurah().forEach{
            val surah = SurahEntity()
            surah.nama = it.nama
            surah.asma = it.asma
            surah.arti = it.arti
            surah.nomor = it.nomor.toInt()
            surah.ayat = it.ayat
            surah.audio = it.audio
            surah.type = it.type
            listSurahRes.add(surah)
        }
        listSurah.value = listSurahRes
        return listSurah
    }

}
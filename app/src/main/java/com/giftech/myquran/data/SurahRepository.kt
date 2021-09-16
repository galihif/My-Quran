package com.giftech.myquran.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.giftech.myquran.data.source.local.entity.SurahEntity
import com.giftech.myquran.data.source.remote.RemoteDataSource
import com.giftech.myquran.data.source.remote.response.ListSurahResponseItem

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
        remoteDataSource.getAllSurah(object : RemoteDataSource.loadAllSurahCallback{
            override fun onResponseReceived(res: List<ListSurahResponseItem>) {
                res.forEach{
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
                listSurah.postValue(listSurahRes)
            }
        })
        return listSurah
    }

}
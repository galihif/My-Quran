package com.giftech.myquran.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.giftech.myquran.data.source.local.entity.AyatEntity
import com.giftech.myquran.data.source.local.entity.LastReadAyatEntity
import com.giftech.myquran.data.source.local.entity.SurahEntity
import com.giftech.myquran.data.source.local.preferences.Preferences
import com.giftech.myquran.data.source.remote.RemoteDataSource
import com.giftech.myquran.data.source.remote.response.AyatResponseItem
import com.giftech.myquran.data.source.remote.response.SurahResponseItem

class SurahRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val preferences: Preferences
):SurahDataSource{


    private val _lastReadAyat = MutableLiveData<LastReadAyatEntity>()
//    val lastReadAyat:LiveData<LastReadAyatEntity>
//        get() = _lastReadAyat

    companion object {
        @Volatile
        private var instance: SurahRepository? = null
        fun getInstance(remoteData: RemoteDataSource, prefs:Preferences): SurahRepository =
            instance ?: synchronized(this) {
                instance ?: SurahRepository(remoteData,prefs).apply { instance = this }
            }
    }

    override fun getAllSurah(): LiveData<List<SurahEntity>> {
        val listSurah = MutableLiveData<List<SurahEntity>>()
        val listSurahRes = ArrayList<SurahEntity>()
        remoteDataSource.getAllSurah(object : RemoteDataSource.loadAllSurahCallback{
            override fun onResponseReceived(res: List<SurahResponseItem>) {
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

    override fun getAyatByNomorSurah(nomorSurah: Int): LiveData<List<AyatEntity>> {
        val listAyat = MutableLiveData<List<AyatEntity>>()
        val listAyatRes = ArrayList<AyatEntity>()
        remoteDataSource.getAyatByNomorSurah(nomorSurah, object : RemoteDataSource.loadAllAyatCallback{
            override fun onResponseReceived(res: List<AyatResponseItem>) {
                res.forEach {
                    val ayat = AyatEntity()
                    ayat.id = it.id
                    ayat.arab = it.ar
                    ayat.nomor = it.nomor.toInt()
                    ayat.nomorSurah = nomorSurah
                    listAyatRes.add(ayat)
                }
                listAyat.postValue(listAyatRes)
            }
        })

        return listAyat
    }

    override fun setLastRead(ayat: LastReadAyatEntity) {
        _lastReadAyat.postValue(ayat)
        preferences.setAyat(ayat)
    }

    override fun getLastRead(): LiveData<LastReadAyatEntity> {
        _lastReadAyat.postValue(preferences.getAyat())
        return _lastReadAyat
    }

    override fun getIsFirstLaunch(): Boolean {
        return preferences.getIfFirstLaunch()
    }

    override fun setFirstLaunch() {
        preferences.setFirstLaunch()
    }


}
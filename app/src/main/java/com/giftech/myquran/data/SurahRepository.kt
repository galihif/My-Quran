package com.giftech.myquran.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.giftech.myquran.data.source.local.LocalDataSource
import com.giftech.myquran.data.source.local.entity.AyatEntity
import com.giftech.myquran.data.source.local.entity.LastReadAyatEntity
import com.giftech.myquran.data.source.local.entity.SurahEntity
import com.giftech.myquran.data.source.remote.RemoteDataSource
import com.giftech.myquran.data.source.remote.api.ApiResponse
import com.giftech.myquran.data.source.remote.response.AyatResponseItem
import com.giftech.myquran.data.source.remote.response.SurahResponseItem
import com.giftech.myquran.utils.AppExecutors
import com.giftech.myquran.utils.DataMapper

class SurahRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
):SurahDataSource{


    private val _lastReadAyat = MutableLiveData<LastReadAyatEntity>()
//    val lastReadAyat:LiveData<LastReadAyatEntity>
//        get() = _lastReadAyat
    private val _lastSurah = MutableLiveData<SurahEntity>()

    companion object {
        @Volatile
        private var instance: SurahRepository? = null
        fun getInstance(remoteData: RemoteDataSource,
                        localData: LocalDataSource,
                        appExecutors: AppExecutors): SurahRepository =
            instance ?: synchronized(this) {
                instance ?: SurahRepository(remoteData,localData, appExecutors).apply { instance = this }
            }
    }

//    override fun getAllSurah(): LiveData<List<SurahEntity>> {
//        val listSurah = MutableLiveData<List<SurahEntity>>()
//        val listSurahRes = ArrayList<SurahEntity>()
//        remoteDataSource.getAllSurah(object : RemoteDataSource.loadAllSurahCallback{
//            override fun onResponseReceived(res: List<SurahResponseItem>) {
//                res.forEach{
//                    val surah = SurahEntity()
//                    surah.nama = it.nama
//                    surah.asma = it.asma
//                    surah.arti = it.arti
//                    surah.nomor = it.nomor.toInt()
//                    surah.ayat = it.ayat
//                    surah.audio = it.audio
//                    surah.type = it.type
//                    listSurahRes.add(surah)
//                }
//                listSurah.postValue(listSurahRes)
//            }
//        })
//        return listSurah
//    }
//
    override fun getAllSurah(): LiveData<Resource<List<SurahEntity>>> =
        object : NetworkBoundResource<List<SurahEntity>, List<SurahResponseItem>>(appExecutors){
            override fun loadFromDB(): LiveData<List<SurahEntity>> {
                return localDataSource.getAllSurah()
            }

            override fun shouldFetch(data: List<SurahEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<SurahResponseItem>>> =
                remoteDataSource.getAllSurah()

            override fun saveCallResult(data: List<SurahResponseItem>) {
                val listSurah = DataMapper.mapListSurahResponseToEntity(data)
//                localDataSource.insertSurah(listSurah)
            }

        }.asLiveData()

    override fun getSurahByName(keyword: String): LiveData<List<SurahEntity>> {
        return localDataSource.getSurahByName(keyword)
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

    override fun setLastSurah(surah: SurahEntity) {
        _lastSurah.postValue(surah)
        localDataSource.setLastSurah(surah)
    }

    override fun getLastSurah(): LiveData<SurahEntity> {
        _lastSurah.postValue(localDataSource.getLastSurah())
        return _lastSurah
    }

    override fun setLastRead(ayat: LastReadAyatEntity) {
        _lastReadAyat.postValue(ayat)
        localDataSource.setLastAyat(ayat)
    }

    override fun getLastRead(): LiveData<LastReadAyatEntity> {
        _lastReadAyat.postValue(localDataSource.getLastAyat())
        return _lastReadAyat
    }

    override fun getIsFirstLaunch(): Boolean {
        return localDataSource.getIsFirstLaunch()
    }

    override fun setFirstLaunch() {
        localDataSource.setFirstLaunch()
    }


}
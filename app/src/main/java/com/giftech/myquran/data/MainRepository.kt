package com.giftech.myquran.data

import com.giftech.myquran.data.model.LastRead
import com.giftech.myquran.data.model.Surah
import com.giftech.myquran.data.model.toEntity
import com.giftech.myquran.data.source.local.LocalDataSource
import com.giftech.myquran.data.source.local.entity.toModel
import com.giftech.myquran.data.source.remote.RemoteDataSource
import com.giftech.myquran.data.source.remote.dto.toEntity
import com.giftech.myquran.data.source.remote.dto.toModel
import com.giftech.myquran.utils.Resource
import com.giftech.myquran.utils.networkBoundResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource
) {

    fun getSearchResult(keyword:String):Flow<List<Surah>> =
        flow{
            local.getSurahByName(keyword)
                .collect{
                    emit(it.map { entity -> entity.toModel() })
                }
        }


    fun getListSurah(): Flow<Resource<List<Surah>>> =
        networkBoundResource(
            query = {
                flow {
                    local.getListSurah().collect {
                        emit(it.map { surahEntity -> surahEntity.toModel() })
                    }
                }
            },
            shouldFetch = {
                it.isEmpty()
            },
            fetch = {
                remote.getListSurah()
            },
            saveFetchResult = { listSurahDto ->
                local.insertNewSurah(listSurahDto.map { surahDto -> surahDto.toEntity() })
            }
        )

    suspend fun getDetailSurah(nomorSurah: Int): Flow<Surah> =
        flowOf(
            remote
                .getDetailSurah(nomorSurah)
                .toModel()
        )

    suspend fun getLastRead(): Flow<LastRead> =
        flowOf(
            local.getLastAyat().toModel()
        )

    fun setLastRead(lastRead: LastRead) {
        local.setLastAyat(lastRead.toEntity())
    }
}
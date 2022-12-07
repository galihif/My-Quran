package com.giftech.myquran.data

import com.giftech.myquran.data.model.Surah
import com.giftech.myquran.data.source.remote.RemoteDataSource
import com.giftech.myquran.data.source.remote.dto.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val remote: RemoteDataSource
) {
    fun test() = "test"

    suspend fun getListSurah(): Flow<List<Surah>> =
        flowOf(
            remote.getListSurah().map { it.toModel() }
        )

    suspend fun getDetailSurah(nomorSurah:Int):Flow<Surah> =
        flowOf(
            remote
                .getDetailSurah(nomorSurah)
                .toModel()
        )
}
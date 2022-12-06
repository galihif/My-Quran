package com.giftech.myquran.data

import com.giftech.myquran.data.source.local.entity.SurahEntity
import com.giftech.myquran.data.source.remote.RemoteDataSource
import com.giftech.myquran.data.source.remote.api.ApiService
import com.giftech.myquran.data.source.remote.response.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val remote: RemoteDataSource
) {
    fun test() = "test"

    suspend fun getListSurah(): Flow<List<SurahEntity>> =
        flowOf(
            remote
                .getListSurah()
                .listSurahResponseItem
                .map { it.toEntity() }
        )
}
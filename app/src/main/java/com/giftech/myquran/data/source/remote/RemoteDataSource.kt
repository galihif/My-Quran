package com.giftech.myquran.data.source.remote

import com.giftech.myquran.data.source.remote.api.ApiService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val api:ApiService
) {
    suspend fun getListSurah() = api.getListSurah()

    suspend fun getDetailSurah(nomorSurah: Int) = api.getDetailSurah(nomorSurah)


}
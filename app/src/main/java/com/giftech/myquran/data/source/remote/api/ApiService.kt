package com.giftech.myquran.data.source.remote.api

import com.giftech.myquran.data.source.remote.dto.DetailSurahDto
import com.giftech.myquran.data.source.remote.dto.SurahDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("surat")
    suspend fun getListSurah(): List<SurahDto>

    @GET("surat/{nomorSurah}")
    suspend fun getDetailSurah(
        @Path("nomorSurah") nomorSurah: Int
    ): DetailSurahDto
}
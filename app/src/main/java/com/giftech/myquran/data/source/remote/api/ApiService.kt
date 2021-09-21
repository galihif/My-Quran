package com.giftech.myquran.data.source.remote.api

import com.giftech.myquran.data.source.remote.response.ListAyatResponse
import com.giftech.myquran.data.source.remote.response.ListSurahResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("/surah")
    fun getAllSurah():Call<ListSurahResponse>

    @GET("/surah/:nomorSurah")
    fun getAyatByNomorSurah(nomorSurah:Int):Call<ListAyatResponse>

}
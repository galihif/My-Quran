package com.giftech.myquran.data

import com.giftech.myquran.data.source.remote.api.ApiService
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val api:ApiService
){
    fun test() = "test"
}
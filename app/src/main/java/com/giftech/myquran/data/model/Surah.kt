package com.giftech.myquran.data.model

data class Surah(
    val nomor:Int,
    val asma:String,
    val nama:String,
    val jumlahAyat:Int,
    val type:String,
    val arti:String,
    val audio:String,
    val desc:String
)
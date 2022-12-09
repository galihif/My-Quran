package com.giftech.myquran.data.source.remote.dto


import com.giftech.myquran.data.model.Surah
import com.giftech.myquran.data.source.local.entity.SurahEntity
import com.google.gson.annotations.SerializedName

class SurahDto(
    @SerializedName("arti")
    val arti: String,
    @SerializedName("audio")
    val audio: String,
    @SerializedName("deskripsi")
    val deskripsi: String,
    @SerializedName("jumlah_ayat")
    val jumlahAyat: Int,
    @SerializedName("nama")
    val nama: String,
    @SerializedName("nama_latin")
    val namaLatin: String,
    @SerializedName("nomor")
    val nomor: Int,
    @SerializedName("tempat_turun")
    val tempatTurun: String
)

fun SurahDto.toModel():Surah =
    Surah(
        nomor = nomor,
        asma = nama,
        nama = namaLatin,
        jumlahAyat = jumlahAyat,
        type = tempatTurun,
        arti = arti,
        audio = audio,
        desc = deskripsi
    )

fun SurahDto.toEntity():SurahEntity =
    SurahEntity(
        nomor = nomor,
        asma = nama,
        nama = namaLatin,
        ayat = jumlahAyat,
        type = tempatTurun,
        arti = arti,
        audio = audio,
    )
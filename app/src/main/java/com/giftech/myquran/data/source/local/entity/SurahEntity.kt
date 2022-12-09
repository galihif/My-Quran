package com.giftech.myquran.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.giftech.myquran.data.model.Surah
import kotlinx.parcelize.Parcelize

@Entity(tableName = "surah")
@Parcelize
data class SurahEntity (

    @ColumnInfo(name = "nama")
    var nama:String="",

    @ColumnInfo(name = "asma")
    var asma:String="",

    @ColumnInfo(name = "arti")
    var arti:String="",

    @ColumnInfo(name = "ayat")
    var ayat:Int=0,

    @PrimaryKey
    @ColumnInfo(name = "nomor")
    var nomor:Int=0,

    @ColumnInfo(name = "audio")
    var audio:String="",

    @ColumnInfo(name = "type")
    var type:String=""

        ):Parcelable

fun SurahEntity.toModel():Surah =
    Surah(
        nomor = nomor,
        asma = asma,
        nama = nama,
        jumlahAyat = ayat,
        type = type,
        arti = arti,
        audio = audio,
        desc = ""
    )
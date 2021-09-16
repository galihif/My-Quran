package com.giftech.myquran.data.source.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SurahEntity (
    var nama:String="",
    var asma:String="",
    var arti:String="",
    var ayat:Int=0,
    var nomor:Int=0,
    var audio:String="",
    var type:String=""
        ):Parcelable
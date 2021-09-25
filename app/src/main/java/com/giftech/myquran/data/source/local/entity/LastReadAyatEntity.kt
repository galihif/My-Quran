package com.giftech.myquran.data.source.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class LastReadAyatEntity (
    var nomorAyat:Int = 0,
    var nomorSurah:Int = 0,
    var namaSurah:String =""
        ):Parcelable
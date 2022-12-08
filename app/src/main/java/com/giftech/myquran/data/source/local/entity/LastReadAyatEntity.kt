package com.giftech.myquran.data.source.local.entity

import android.os.Parcelable
import com.giftech.myquran.data.model.LastRead
import kotlinx.parcelize.Parcelize

@Parcelize
class LastReadAyatEntity (
    var nomorAyat:Int = 0,
    var nomorSurah:Int = 0,
    var namaSurah:String =""
        ):Parcelable

fun LastReadAyatEntity.toModel():LastRead =
    LastRead(
        nomorAyat = nomorAyat,
        nomorSurah = nomorSurah,
        namaSurah = namaSurah
    )
package com.giftech.myquran.data.model

import com.giftech.myquran.data.source.local.entity.LastReadAyatEntity

data class LastRead(
    val nomorAyat:Int = 1,
    val nomorSurah:Int = 1,
    val namaSurah:String = "Al-Fatihah"
)

fun LastRead.toEntity():LastReadAyatEntity =
    LastReadAyatEntity(
        nomorAyat = nomorAyat,
        nomorSurah = nomorSurah,
        namaSurah = namaSurah
    )

package com.giftech.myquran.data.source.local.entity

import com.giftech.myquran.data.model.LastRead

class LastReadAyatEntity(
    var nomorAyat: Int = 0,
    var nomorSurah: Int = 0,
    var namaSurah: String = ""
)

fun LastReadAyatEntity.toModel(): LastRead =
    LastRead(
        nomorAyat = nomorAyat,
        nomorSurah = nomorSurah,
        namaSurah = namaSurah
    )
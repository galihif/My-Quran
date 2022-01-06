package com.giftech.myquran.utils

import com.giftech.myquran.data.source.local.entity.SurahEntity
import com.giftech.myquran.data.source.remote.response.SurahResponseItem

object DataMapper {

    fun mapListSurahResponseToEntity(input:List<SurahResponseItem>): List<SurahEntity>{
        val listSurahEntity = ArrayList<SurahEntity>()
        input.map {
            val surah = SurahEntity()
            surah.nama = it.nama
            surah.asma = it.asma
            surah.arti = it.arti
            surah.nomor = it.nomor.toInt()
            surah.ayat = it.ayat
            surah.audio = it.audio
            surah.type = it.type
            listSurahEntity.add(surah)
        }
        return listSurahEntity
    }

}
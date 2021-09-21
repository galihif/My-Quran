package com.giftech.myquran.utils

import com.giftech.myquran.data.source.remote.response.SurahResponseItem

object DummySurah {

    fun generateRemoteDummyListSurah():List<SurahResponseItem>{
        val listSurah = ArrayList<SurahResponseItem>()

        listSurah.add(
            SurahResponseItem(
                arti= "Pembukaan",
                asma= "الفاتحة",
                audio= "http://ia802609.us.archive.org/13/items/quraninindonesia/001AlFaatihah.mp3",
                ayat= 7,
                keterangan= "Surat <i>Al Faatihah</i> (Pembukaan) yang diturunkan di Mekah dan terdiri dari 7 ayat adalah surat yang pertama-tama diturunkan dengan lengkap diantara surat-surat yang ada dalam Al Quran dan termasuk golongan surat Makkiyyah. Surat ini disebut <i>Al Faatihah</i> (Pembukaan), karena dengan surat inilah dibuka dan dimulainya Al Quran. Dinamakan <i>Ummul Quran</i> (induk Al Quran) atau <i>Ummul Kitaab</i> (induk Al Kitaab) karena dia merupakan induk dari semua isi Al Quran, dan karena itu diwajibkan membacanya pada tiap-tiap sembahyang.<br> Dinamakan pula <i>As Sab'ul matsaany</i> (tujuh yang berulang-ulang) karena ayatnya tujuh dan dibaca berulang-ulang dalam sholat.",
                nama= "Al Fatihah",
                nomor= "1",
                rukuk= "1",
                type= "mekah",
                urut= "5"
            )
        )
        listSurah.add(
            SurahResponseItem(
                arti= "Sapi Betina",
                asma= "البقرة",
                audio= "http://ia802609.us.archive.org/13/items/quraninindonesia/002AlBaqarah.mp3",
                ayat= 286,
                keterangan= "Surat <i>Al Faatihah</i> (Pembukaan) yang diturunkan di Mekah dan terdiri dari 7 ayat adalah surat yang pertama-tama diturunkan dengan lengkap diantara surat-surat yang ada dalam Al Quran dan termasuk golongan surat Makkiyyah. Surat ini disebut <i>Al Faatihah</i> (Pembukaan), karena dengan surat inilah dibuka dan dimulainya Al Quran. Dinamakan <i>Ummul Quran</i> (induk Al Quran) atau <i>Ummul Kitaab</i> (induk Al Kitaab) karena dia merupakan induk dari semua isi Al Quran, dan karena itu diwajibkan membacanya pada tiap-tiap sembahyang.<br> Dinamakan pula <i>As Sab'ul matsaany</i> (tujuh yang berulang-ulang) karena ayatnya tujuh dan dibaca berulang-ulang dalam sholat.",
                nama= "Al Baqarah",
                nomor= "2",
                rukuk= "40",
                type= "madinah",
                urut= "87"
            )
        )

        return listSurah

    }

}
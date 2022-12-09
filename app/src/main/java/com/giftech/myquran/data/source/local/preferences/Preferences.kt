package com.giftech.myquran.data.source.local.preferences

import android.content.Context
import com.giftech.myquran.data.source.local.entity.LastReadAyatEntity

class Preferences(context: Context) {
    companion object {
        private const val PREFS_NAME = "ayat_pref"
        private const val NOMOR_AYAT = "NOMOR_AYAT"
        private const val NOMOR_SURAH = "NOMOR_SURAH"
        private const val NAMA_SURAH = "NAMA_SURAH"
    }

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setAyat(ayat:LastReadAyatEntity){
        val editor = preferences.edit()
        editor.putInt(NOMOR_AYAT, ayat.nomorAyat)
        editor.putInt(NOMOR_SURAH, ayat.nomorSurah)
        editor.putString(NAMA_SURAH, ayat.namaSurah)
        editor.apply()
    }

    fun getAyat():LastReadAyatEntity{
        val ayat = LastReadAyatEntity()
        ayat.nomorAyat = preferences.getInt(NOMOR_AYAT, 1)
        ayat.nomorSurah = preferences.getInt(NOMOR_SURAH,1)
        ayat.namaSurah = preferences.getString(NAMA_SURAH,"Al-Fatihah")!!
        return ayat
    }
}
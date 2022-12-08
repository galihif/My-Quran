package com.giftech.myquran.data.source.local.preferences

import android.annotation.SuppressLint
import android.content.Context
import com.giftech.myquran.data.source.local.entity.LastReadAyatEntity
import com.giftech.myquran.data.source.local.entity.SurahEntity

class Preferences(val context: Context) {
    companion object {
        private const val PREFS_NAME = "ayat_pref"
        private const val NOMOR_AYAT = "NOMOR_AYAT"
        private const val NOMOR_SURAH = "NOMOR_SURAH"
        private const val NAMA_SURAH = "NAMA_SURAH"
        private const val FIRST_LAUNCH = "FIRST_LAUNCH"

        private const val ASMA_SURAH="ASMA_SURAH"
        private const val ARTI_SURAH="ARTI_SURAH"
        private const val AYAT_SURAH="AYAT_SURAH"
        private const val AUDIO_SURAH="AUDIO_SURAH"
        private const val TYPE_SURAH="TYPE_SURAH"

        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var instance: Preferences? = null
        fun getInstance(mContext:Context): Preferences =
            instance ?: synchronized(this) {
                instance ?: Preferences(mContext.applicationContext).apply { instance = this }
            }
    }

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setFirstLaunch(){
        val editor = preferences.edit()
        editor.putBoolean(FIRST_LAUNCH, false)
        editor.apply()
    }

    fun getIfFirstLaunch():Boolean{
        return  preferences.getBoolean(FIRST_LAUNCH,true)
    }

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

    fun setSurah(surah:SurahEntity){
        preferences.edit().apply {
            putString(NAMA_SURAH,surah.nama)
            putString(ASMA_SURAH,surah.asma)
            putString(ARTI_SURAH,surah.arti)
            putInt(AYAT_SURAH, surah.ayat)
            putInt(NOMOR_SURAH, surah.nomor)
            putString(AUDIO_SURAH, surah.audio)
            putString(TYPE_SURAH, surah.type)
        }.apply()
    }

    fun getSurah(): SurahEntity {
        return SurahEntity(
            preferences.getString(NAMA_SURAH, "")!!,
            preferences.getString(ASMA_SURAH, "")!!,
            preferences.getString(ARTI_SURAH, "")!!,
            preferences.getInt(AYAT_SURAH, 0),
            preferences.getInt(NOMOR_SURAH, 0),
            preferences.getString(AUDIO_SURAH, "")!!,
            preferences.getString(TYPE_SURAH, "")!!,

            )
    }
}
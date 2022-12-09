package com.giftech.myquran.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.giftech.myquran.data.source.local.entity.SurahEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SurahDao {

    @Query("SELECT * FROM surah")
    fun getAllSurah(): LiveData<List<SurahEntity>>

    @Query("SELECT * FROM surah")
    fun getListSurah(): Flow<List<SurahEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSurah(listSurah: List<SurahEntity>)

    @Query("SELECT * FROM surah WHERE nama LIKE '%' || :keyword || '%'")
    fun getSurahByName(keyword:String):Flow<List<SurahEntity>>

    @Query("DELETE FROM surah")
    suspend fun deleteAllSurah()

}
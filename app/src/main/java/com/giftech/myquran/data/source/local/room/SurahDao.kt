package com.giftech.myquran.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.giftech.myquran.data.source.local.entity.SurahEntity

@Dao
interface SurahDao {

    @Query("SELECT * FROM surah")
    fun getAllSurah(): LiveData<List<SurahEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSurah(listSurah: List<SurahEntity>)

}
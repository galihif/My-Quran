package com.giftech.myquran.di

import android.content.Context
import com.giftech.myquran.data.SurahRepository
import com.giftech.myquran.data.source.local.LocalDataSource
import com.giftech.myquran.data.source.local.preferences.Preferences
import com.giftech.myquran.data.source.local.room.SurahDatabase
import com.giftech.myquran.data.source.remote.RemoteDataSource
import com.giftech.myquran.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): SurahRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = provideLocalDataSource(context)
        val appExecutors = AppExecutors()

        return SurahRepository.getInstance(remoteDataSource,localDataSource, appExecutors)
    }

    private fun provideLocalDataSource(context: Context):LocalDataSource {
        val database = SurahDatabase.getInstance(context)

        val dao = database.surahDao()
        val preferences = Preferences.getInstance(context)

        return LocalDataSource.getInstance(dao, preferences)
    }
}
package com.giftech.myquran.di

import android.content.Context
import com.giftech.myquran.data.SurahRepository
import com.giftech.myquran.data.source.local.preferences.Preferences
import com.giftech.myquran.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(context: Context): SurahRepository {

//        val database = FilmDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val preferences = Preferences.getInstance(context)
//        val localDataSource = LocalDataSource.getInstance(database.filmDao())
//        val appExecutors = AppExecutors()

        return SurahRepository.getInstance(remoteDataSource,preferences)
    }
}
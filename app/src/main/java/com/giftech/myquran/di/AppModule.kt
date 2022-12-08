package com.giftech.myquran.di

import android.content.Context
import androidx.room.Room
import com.giftech.myquran.data.MainRepository
import com.giftech.myquran.data.source.local.LocalDataSource
import com.giftech.myquran.data.source.local.preferences.Preferences
import com.giftech.myquran.data.source.local.room.SurahDao
import com.giftech.myquran.data.source.local.room.SurahDatabase
import com.giftech.myquran.data.source.remote.RemoteDataSource
import com.giftech.myquran.data.source.remote.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePreferences(@ApplicationContext context: Context):Preferences =
        Preferences(context.applicationContext)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context):SurahDatabase  =
        Room.databaseBuilder(
            context.applicationContext,
            SurahDatabase::class.java,
            "Surah.db"
        )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideDao(database: SurahDatabase):SurahDao =
        database.surahDao()

    @Provides
    @Singleton
    fun provideLocal(dao:SurahDao, pref:Preferences):LocalDataSource  =
        LocalDataSource(dao,pref)


    @Provides
    @Singleton
    fun provideApiService():ApiService{
        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://equran.id/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(api: ApiService):RemoteDataSource = RemoteDataSource(api)

    @Provides
    @Singleton
    fun provideRepository(
        local:LocalDataSource,
        remote:RemoteDataSource
    ) = MainRepository(local,remote)
}
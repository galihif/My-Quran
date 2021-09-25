package com.giftech.myquran.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.giftech.myquran.data.SurahRepository
import com.giftech.myquran.di.Injection
import com.giftech.myquran.ui.home.HomeViewModel
import com.giftech.myquran.ui.surah.SurahViewModel

class ViewModelFactory private constructor(private val mSurahRepository: SurahRepository, private val mContext:Context)
    : ViewModelProvider.NewInstanceFactory(){

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory {
            return instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context),context).apply {
                    instance = this
                }
            }
        }

    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when{
            modelClass.isAssignableFrom(HomeViewModel::class.java)->{
                return HomeViewModel(mSurahRepository,mContext) as T
            }
            modelClass.isAssignableFrom(SurahViewModel::class.java)->{
                return SurahViewModel(mSurahRepository) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }

}
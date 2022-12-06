package com.giftech.myquran.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.giftech.myquran.data.source.remote.api.ApiConfig
import com.giftech.myquran.data.source.remote.api.ApiResponse
import com.giftech.myquran.data.source.remote.api.ApiService
import com.giftech.myquran.data.source.remote.response.AyatResponseItem
import com.giftech.myquran.data.source.remote.response.ListAyatResponse
import com.giftech.myquran.data.source.remote.response.ListSurahResponse
import com.giftech.myquran.data.source.remote.response.SurahResponseItem
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val api:ApiService
) {

    companion object{
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(ApiConfig.getApiService()).apply { instance = this }
            }
    }

//    fun getAllSurah(callback: loadAllSurahCallback){
//        val client = ApiConfig.getApiService().getAllSurah()
//        client.enqueue(object :retrofit2.Callback<ListSurahResponse>{
//            override fun onResponse(
//                call: Call<ListSurahResponse>,
//                response: Response<ListSurahResponse>
//            ) {
//                if(response.isSuccessful){
//                    val listSurahResponseItem = response.body()?.listSurahResponseItem
//                    callback.onResponseReceived(listSurahResponseItem!!)
//                }
//            }
//
//            override fun onFailure(call: Call<ListSurahResponse>, t: Throwable) {
//                Log.e("TAG", "onFailure: ${t.message.toString()}")
//            }
//        })
//    }
//

    suspend fun getListSurah() = api.getListSurah()

    fun getAllSurah(): LiveData<ApiResponse<List<SurahResponseItem>>> {
        val resultData = MutableLiveData<ApiResponse<List<SurahResponseItem>>>()
        val client = ApiConfig.getApiService().getAllSurah()

        client.enqueue(object : retrofit2.Callback<ListSurahResponse>{
            override fun onResponse(
                call: Call<ListSurahResponse>,
                response: Response<ListSurahResponse>
            ) {
                if (response.isSuccessful){
                    val listSurahResponseItem = response.body()?.listSurahResponseItem
                    resultData.value = when{
                        listSurahResponseItem != null -> ApiResponse.Success(listSurahResponseItem)
                        else -> ApiResponse.Empty
                    }
                }
            }

            override fun onFailure(call: Call<ListSurahResponse>, t: Throwable) {
                resultData.value = ApiResponse.Error(t.message.toString())
                Log.e("TAG", "onFailure: ${t.message.toString()}")
            }

        })

        return resultData
    }


    fun getAyatByNomorSurah(nomorSurah:Int, callback: loadAllAyatCallback){
        val client = ApiConfig.getApiService().getAyatByNomorSurah(nomorSurah)
        client.enqueue(object : retrofit2.Callback<ListAyatResponse>{
            override fun onResponse(
                call: Call<ListAyatResponse>,
                response: Response<ListAyatResponse>
            ) {
                if(response.isSuccessful){
                    val listAyatResponseItem = response.body()?.listAyatResponseItem
                    callback.onResponseReceived(listAyatResponseItem!!)
                }
            }

            override fun onFailure(call: Call<ListAyatResponse>, t: Throwable) {
                Log.e("TAG", "onFailure: ${t.message.toString()}")
            }

        })
    }

    interface loadAllSurahCallback{
        fun onResponseReceived(res:List<SurahResponseItem>)
    }
    interface loadAllAyatCallback{
        fun onResponseReceived(res:List<AyatResponseItem>)
    }

}
package com.giftech.myquran.data.source.remote

import com.giftech.myquran.data.source.remote.response.ListSurahResponseItem
import com.giftech.myquran.utils.DummySurah

class RemoteDataSource {

    companion object{
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource().apply { instance = this }
            }
    }

    fun getAllSurah():List<ListSurahResponseItem>{
        val listSurah = ArrayList<ListSurahResponseItem>()
//        val client = ApiConfig.getApiService().getAllSurah()
//        client.enqueue(object :retrofit2.Callback<ListSurahResponse>{
//            override fun onResponse(
//                call: Call<ListSurahResponse>,
//                response: Response<ListSurahResponse>
//            ) {
//                if(response.isSuccessful){
//                    val listSurahResponseItem = response.body()?.listSurahResponseItem
//                    if (listSurahResponseItem != null) {
//                        listSurah.addAll(listSurahResponseItem)
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<ListSurahResponse>, t: Throwable) {
//                Log.e("TAG", "onFailure: ${t.message.toString()}")
//            }
//
//        })
        val listSurahRes = DummySurah.generateRemoteDummyListSurah()
        listSurah.addAll(listSurahRes)
        return listSurah
    }

}
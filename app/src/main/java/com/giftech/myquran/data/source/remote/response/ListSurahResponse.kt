package com.giftech.myquran.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListSurahResponse(

	@field:SerializedName("data")
	val listSurahResponseItem: List<SurahResponseItem>
)

data class SurahResponseItem(

	@field:SerializedName("keterangan")
	val keterangan: String,

	@field:SerializedName("rukuk")
	val rukuk: String,

	@field:SerializedName("nama")
	val nama: String,

	@field:SerializedName("ayat")
	val ayat: Int,

	@field:SerializedName("urut")
	val urut: String,

	@field:SerializedName("arti")
	val arti: String,

	@field:SerializedName("asma")
	val asma: String,

	@field:SerializedName("audio")
	val audio: String,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("nomor")
	val nomor: String
)

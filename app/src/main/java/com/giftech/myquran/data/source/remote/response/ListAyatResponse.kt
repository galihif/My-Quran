package com.giftech.myquran.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListAyatResponse(

	@field:SerializedName("data")
	val listAyatResponseItem: List<AyatResponseItem>
)

data class AyatResponseItem(

	@field:SerializedName("ar")
	val ar: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("nomor")
	val nomor: String,

	@field:SerializedName("tr")
	val tr: String
)

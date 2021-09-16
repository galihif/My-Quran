package com.giftech.myquran.data.source.remote.response

data class ListAyatResponse(
	val listAyatResponse: List<ListAyatResponseItem>
)

data class ListAyatResponseItem(
	val ar: String,
	val id: String,
	val nomor: String,
	val tr: String
)


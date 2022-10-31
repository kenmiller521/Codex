package com.zil.codex.shared.api.services

import retrofit2.Call
import retrofit2.http.GET

interface CardService {
	@GET("/api/v5/AllPrintings.json")
	fun getAllPrintings(): Call<Any>
}

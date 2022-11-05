package com.zil.codex.shared.api.services

import com.zil.codex.shared.data.models.MTGSet
import com.zil.codex.shared.data.models.MTGSetList
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Streaming

interface CardService {
	@GET("/api/v5/10E.json")
	fun get10E(): Call<MTGSet>

	@GET("/api/v5/SetList.json")
	fun getSetList(): Call<MTGSetList>

	@Streaming
	@GET("/api/v5/AllPrintings.json")
	fun getAllPrintings(): Call<ResponseBody>
}

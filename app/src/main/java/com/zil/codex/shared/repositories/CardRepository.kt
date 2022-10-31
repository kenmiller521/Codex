package com.zil.codex.shared.repositories

import android.util.Log
import com.zil.codex.shared.api.services.CardService
import com.zil.codex.shared.composables.atoms.hilt.modules.ApiModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import javax.inject.Inject

class CardRepository @Inject constructor(
	@ApiModule.CodexRetrofitClient private val api: Retrofit
){
	private var cardService: CardService = api.create(CardService::class.java)

	suspend fun fetchCard() = withContext(Dispatchers.IO){
		"repo test 2"
	}

	suspend fun fetchAllPrints() = withContext(Dispatchers.IO){
		val response = cardService.getAllPrintings().execute().body()
		Log.d("zxcv","reponse: $response")
		response
	}
}

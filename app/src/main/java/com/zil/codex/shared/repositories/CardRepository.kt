package com.zil.codex.shared.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CardRepository @Inject constructor(
){
	suspend fun fetchCard() = withContext(Dispatchers.IO){
		"repo test 2"
	}
}

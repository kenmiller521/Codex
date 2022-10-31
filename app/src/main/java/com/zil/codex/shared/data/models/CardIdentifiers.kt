package com.zil.codex.shared.data.models

import com.google.gson.annotations.SerializedName

class CardIdentifiers(
	@SerializedName("scryfallId")
	val scryfallId: String,
	@SerializedName("tcgplayerProductId")
	val tcgplayerProductId: String,
)

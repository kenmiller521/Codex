package com.zil.codex.shared.data.models

import com.google.gson.annotations.SerializedName

class MTGSetData(
	@SerializedName("cards")
	val cards: List<Card>
)

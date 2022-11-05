package com.zil.codex.shared.data.models

import com.google.gson.annotations.SerializedName

class MTGSetList(
	@SerializedName("meta")
	val meta: Meta,
	@SerializedName("data")
	val data: List<MTGSet>
)

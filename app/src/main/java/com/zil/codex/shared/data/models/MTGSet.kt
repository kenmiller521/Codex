package com.zil.codex.shared.data.models

import com.google.gson.annotations.SerializedName

class MTGSet(
	@SerializedName("data")
	val data: MTGSetData
)

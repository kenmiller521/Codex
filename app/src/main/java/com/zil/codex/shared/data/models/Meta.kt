package com.zil.codex.shared.data.models

import com.google.gson.annotations.SerializedName

class Meta(
	@SerializedName("date")
	val date: String,
	@SerializedName("version")
	val version: String
)

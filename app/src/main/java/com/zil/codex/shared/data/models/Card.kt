package com.zil.codex.shared.data.models

import com.google.gson.annotations.SerializedName
import com.zil.codex.shared.constants.UrlConstants

class Card(
	@SerializedName("artist")
	val artist: String,
	@SerializedName("availability")
	val availability: List<String>,
	@SerializedName("name")
	val name: String,
	@SerializedName("identifiers")
	val identifiers: CardIdentifiers
) {
	fun getCardAvailabilities(): List<CardAvailability> {
		return availability.map {
			it.toCardAvailability()
		}
	}

	fun getScryfallImageUrl(): String {
		return "${UrlConstants.scryfallBaseUrl}${identifiers.scryfallId}?format=image"
	}
}

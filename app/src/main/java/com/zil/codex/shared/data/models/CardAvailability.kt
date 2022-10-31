package com.zil.codex.shared.data.models

sealed class CardAvailability {
	object MTGO : CardAvailability()
	object Paper : CardAvailability()

	override fun toString(): String {
		return when (this) {
			MTGO -> "mtgo"
			else -> "paper"
		}
	}
}

fun String.toCardAvailability(): CardAvailability {
	return when (this) {
		"mtgo" -> CardAvailability.MTGO
		else -> CardAvailability.Paper
	}
}

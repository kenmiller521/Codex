package com.zil.codex.shared.data.models

import com.google.gson.annotations.SerializedName
import java.util.*

class MTGSet(
	@SerializedName("baseSetSize")
	val baseSetSize: Int,
	@SerializedName("block")
	val block: String,
	@SerializedName("code")
	val code: String,
	@SerializedName("isFoilOnly")
	val isFoilOnly: Boolean,
	@SerializedName("isNonFoilOnly")
	val isNonFoilOnly: Boolean,
	@SerializedName("isOnlineOnly")
	val isOnlineOnly: Boolean,
	@SerializedName("keyruneCode")
	val keyruneCode: String,
	@SerializedName("name")
	val name: String,
	@SerializedName("releaseDate")
	val releaseDate: String,
	@SerializedName("totalSetSize")
	val totalSetSize: String,
	@SerializedName("type")
	val type: String,
) {
	fun getKeyruneUnicode(): String? =
		when(keyruneCode.uppercase(Locale.US)) {
			"PMEI" -> "\ue687"
			"10E" -> "\ue60b"
			"1E" -> "\ue947"
			"2E" -> "\ue948"
			"2ED" -> "\ue602"
			"2U" -> "\ue949"
			"2XM" -> "\ue96e"
			"2X2" -> "\ue99c"
			"3E" -> "\ue99a"
			"3ED" -> "\ue603"
			"40K" -> "\ue998"
			"4ED" -> "\ue604"
			"5DN" -> "\ue633"
			"5ED" -> "\ue606"
			"6ED" -> "\ue607"
			"7ED" -> "\ue608"
			"8ED" -> "\ue609"
			"9ED" -> "\ue60a"
			"A25" -> "\ue93d"
			"AER" -> "\ue90F"
			"AFC" -> "\ue981"
			"AFR" -> "\ue972"
			"AKH" -> "\ue914"
			"AKR" -> "\ue970"
			"ALA" -> "\ue641"
			"ALL" -> "\ue61a"
			"ANN" -> "\ue92d"
			"APC" -> "\ue69a"
			"ARB" -> "\ue643"
			"ARC" -> "\ue657"
			"ARN" -> "\ue613"
			"ATH" -> "\ue65f"
			"ATQ" -> "\ue614"
			"AVR" -> "\ue64c"
			"BBD" -> "\ue942"
			"BFZ" -> "\ue699"
			"BNG" -> "\ue651"
			"BOK" -> "\ue635"
			"BRB" -> "\ue660"
			"BTD" -> "\ue661"
			"C13" -> "\ue65b"
			"C14" -> "\ue65d"
			"C15" -> "\ue900"
			"C16" -> "\ue910"
			"C17" -> "\ue934"
			"C18" -> "\ue946"
			"C19" -> "\ue95f"
			"C20" -> "\ue966"
			"C21" -> "\ue97e"
			"CLB" -> "\ue991"
			"CC1" -> "\ue968"
			"CC2" -> "\ue987"
			"CHK" -> "\ue634"
			"CHR" -> "\ue65e"
			"CM1" -> "\ue65a"
			"CM2" -> "\ue640"
			"CMA" -> "\ue916"
			"CMC" -> "\ue969"
			"CMD" -> "\ue658"
			"CMR" -> "\ue969"
			"CNS" -> "\ue65c"
			"CN2" -> "\ue904"
			"CON" -> "\ue642"
			"CSP" -> "\ue61b"
			"DD2" -> "\ue66a"
			"DDC" -> "\ue66b"
			"DDD" -> "\ue66c"
			"DDE" -> "\ue66d"
			"DDF" -> "\ue66e"
			"DDG" -> "\ue66f"
			"DDH" -> "\ue670"
			"DDI" -> "\ue671"
			"DDJ" -> "\ue672"
			"DDK" -> "\ue673"
			"DDL" -> "\ue674"
			"DDM" -> "\ue675"
			"DDN" -> "\ue676"
			"DDO" -> "\ue677"
			"DDP" -> "\ue698"
			"DDQ" -> "\ue908"
			"DDR" -> "\ue90d"
			"DDS" -> "\ue921"
			"DDT" -> "\ue933"
			"DDU" -> "\ue93e"
			"DGM" -> "\ue64f"
			"DIS" -> "\ue639"
			"DKA" -> "\ue64b"
			"DKM" -> "\ue662"
			"DMU" -> "\ue993"
			"DMC" -> "\ue994"
			"DOM" -> "\ue93f"
			"DPA" -> "\ue689"
			"DRB" -> "\ue678"
			"DRK" -> "\ue616"
			"DST" -> "\ue632"
			else -> null
		}
}

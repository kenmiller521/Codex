package com.zil.codex.shared.composables.atoms

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest

@Composable
fun MTGCardComposable(
	cardName: String,
	scryfallImageUrl: String
) {

	val isLoadingImage = remember { mutableStateOf(true) }
	Log.d("zxcv","Card Name: $cardName / $scryfallImageUrl")
	Box(
		modifier = Modifier.fillMaxSize(),
		contentAlignment = Alignment.Center
	) {
		AsyncImage(
			model = ImageRequest.Builder(LocalContext.current)
				.data(scryfallImageUrl)
				.crossfade(true)
				.diskCachePolicy(CachePolicy.ENABLED)
				.build()
			,
			onSuccess = {
				isLoadingImage.value = false
			},
			contentDescription = "",
		)

		if (isLoadingImage.value) {
			CircularProgressIndicator()
		}
	}
}

@Composable
@Preview
fun CardPreview() {
	MTGCardComposable("","https://api.scryfall.com/cards/7a5cd03c-4227-4551-aa4b-7d119f0468b5?format=image")
}

package com.zil.codex.features.home.composables.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.zil.codex.features.home.view_models.DataState
import com.zil.codex.shared.composables.atoms.MTGCardComposable
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun HomeScreen(
	dataState: MutableStateFlow<DataState> = MutableStateFlow(DataState.Inactive),
	onFetch: () -> Unit
) {
	val lifecycleOwner = LocalLifecycleOwner.current
	val exampleEntitiesFlowLifecycleAware = remember(dataState, lifecycleOwner) {
		dataState.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
	}
	val currentState =
		exampleEntitiesFlowLifecycleAware.collectAsState(initial = DataState.Loading).value

	Box(
		modifier = Modifier,
		contentAlignment = Alignment.Center
	) {
		when (currentState) {
			DataState.Inactive -> Button(onClick = onFetch) {
				Text(
					text = "Fetch Cards"
				)
			}
			DataState.Loading -> CircularProgressIndicator()
			is DataState.ResponseData -> LazyColumn() {
				itemsIndexed(items = currentState.data, itemContent = { index, item ->
					MTGCardComposable(
						cardName = item.name,
						scryfallImageUrl = item.getScryfallImageUrl()
					)
				})

			}
			is DataState.Error -> Text(
				text = "Tis an error: ${currentState.error}"
			)
		}

	}
}

@Composable
@Preview
fun HomeScreenPreview() {
	HomeScreen() {}
}

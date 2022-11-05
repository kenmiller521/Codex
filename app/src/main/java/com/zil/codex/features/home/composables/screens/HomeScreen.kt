package com.zil.codex.features.home.composables.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.zil.codex.features.home.view_models.DataState
import com.zil.codex.shared.composables.atoms.MTGSetCardComposable
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun HomeScreen(
	dataState: MutableStateFlow<DataState> = MutableStateFlow(DataState.Inactive),
	onFetch: () -> Unit,
	onFetchAllPrintings: () -> Unit,
	onMTGSetClick: (setCode: String) -> Unit
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
			DataState.Inactive -> {
				Column {

					Button(onClick = onFetch) {
						Text(
							text = "Fetch Set List"
						)
					}

					Spacer(modifier = Modifier.size(24.dp))

					Button(onClick = onFetchAllPrintings) {
						Text(
							text = "Fetch All Printings"
						)
					}
				}
			}
			DataState.Loading -> CircularProgressIndicator()
			is DataState.ResponseData -> LazyVerticalGrid(
				columns = GridCells.Fixed(2),
				contentPadding = PaddingValues(24.dp),
				verticalArrangement = Arrangement.spacedBy(16.dp),
				horizontalArrangement = Arrangement.spacedBy(16.dp)
			) {
				itemsIndexed(items = currentState.data, itemContent = { index, item ->
					MTGSetCardComposable(set = item) {
						onMTGSetClick.invoke(item.code)
					}
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
	HomeScreen(onFetch = {}, onFetchAllPrintings = {}, onMTGSetClick = {})
}

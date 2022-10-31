package com.zil.codex.features.home.view_models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zil.codex.shared.data.models.Card
import com.zil.codex.shared.repositories.CardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class DataState {
	object Inactive : DataState()
	object Loading : DataState()
	data class ResponseData(val data: List<Card>) : DataState()
	data class Error(val error: String?) : DataState()
}

sealed class DataIntent {
	object FetchData : DataIntent()
}

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
	private val cardRepository: CardRepository
) : ViewModel() {
	val dataIntent = Channel<DataIntent>(Channel.UNLIMITED)
	val dataState = MutableStateFlow<DataState>(DataState.Inactive)

	init {
		handleIntent()
	}

	private fun handleIntent() {
		viewModelScope.launch {
			dataIntent.consumeAsFlow().collect {
				Log.d("zxcv", "handle intent: $it")
				when (it) {
					is DataIntent.FetchData -> fetchData()
				}
			}
		}
	}

	fun fetchData() {
		viewModelScope.launch(Dispatchers.Main) {
			dataState.value = DataState.Loading
			dataState.value = try {
				DataState.ResponseData((cardRepository.fetch10E()?.data?.cards?.distinctBy { it.name })?: emptyList())
			} catch (e: Exception) {
				DataState.Error(e.message)
			}
		}
	}
}

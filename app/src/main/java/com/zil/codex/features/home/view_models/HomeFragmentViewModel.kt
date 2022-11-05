package com.zil.codex.features.home.view_models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zil.codex.shared.data.models.MTGSet
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
	data class ResponseData(val data: List<MTGSet>) : DataState()
	data class Error(val error: String?) : DataState()
}

sealed class DataIntent {
	object FetchData : DataIntent()
	object FetchAllPrintings : DataIntent()
	class FetchSet(val setCode: String): DataIntent()
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
				when (it) {
					is DataIntent.FetchData -> fetchData()
					is DataIntent.FetchAllPrintings -> fetchAllPrintings()
					is DataIntent.FetchSet -> Log.d("zxcv","Set Code: ${it.setCode}")
				}
			}
		}
	}

	private fun fetchData() {
		viewModelScope.launch(Dispatchers.Main) {
			dataState.value = DataState.Loading
			dataState.value = try {
				DataState.ResponseData((cardRepository.fetchSetList()?.data)?:emptyList())
			} catch (e: Exception) {
				DataState.Error(e.message)
			}
		}
	}

	private fun fetchAllPrintings() {
		viewModelScope.launch(Dispatchers.Main) {
			dataState.value = DataState.Loading
			dataState.value = try {
				cardRepository.fetchAllPrintings()
				DataState.ResponseData(emptyList())
			} catch (e: Exception) {
				DataState.Error(e.message)
			}
		}
	}
}

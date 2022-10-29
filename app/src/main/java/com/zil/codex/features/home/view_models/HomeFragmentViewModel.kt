package com.zil.codex.features.home.view_models

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zil.codex.shared.repositories.CardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
	private val cardRepository: CardRepository
) : ViewModel() {
	val testMutableLiveData = MutableLiveData<String>()

	fun test() {
		Log.d("zxcv","test")
		viewModelScope.launch(Dispatchers.Main){
			try {
				val test = cardRepository.fetchCard()
				testMutableLiveData.value = test
			}
			catch(e:Exception) {
				testMutableLiveData.value = "fail"
			}
		}
	}
}

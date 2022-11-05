package com.zil.codex.features.home.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zil.codex.shared.repositories.CardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeActivityViewModel @Inject constructor(
	private val cardRepository: CardRepository
) : ViewModel() {
	val testMute = MutableLiveData<String>()
	fun activitytest() {
		viewModelScope.launch(Dispatchers.Main){
			try{
				//val text = cardRepository.fetchCard()
				//testMute.value = "ACTIVITY $text"
			}
			catch(e:Exception) {

			}
		}
	}
}

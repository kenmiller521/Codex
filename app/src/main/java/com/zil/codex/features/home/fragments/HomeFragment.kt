package com.zil.codex.features.home.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.zil.codex.R
import com.zil.codex.features.home.view_models.HomeFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

	private val viewModel by viewModels<HomeFragmentViewModel>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		return inflater.inflate(R.layout.fragment_main, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		initViewModel()
		viewModel.test()
	}

	private fun initViewModel(){
		viewModel.testMutableLiveData.observe(viewLifecycleOwner, Observer {
			if(it != null) {
				Log.d("zxcv","mutbale live data test: $it")
				viewModel.testMutableLiveData.value = null
			}
		})
	}

	companion object {
		fun newInstance() = HomeFragment()
	}
}

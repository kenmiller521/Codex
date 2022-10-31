package com.zil.codex.features.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.zil.codex.databinding.FragmentGenericComposeViewBinding
import com.zil.codex.features.home.composables.screens.HomeScreen
import com.zil.codex.features.home.view_models.DataIntent
import com.zil.codex.features.home.view_models.HomeFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

	private val viewModel by viewModels<HomeFragmentViewModel>()
	private lateinit var binding: FragmentGenericComposeViewBinding

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentGenericComposeViewBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		initView()
		initViewModel()
	}

	private fun initView() {
		binding.composeView.setContent {
			HomeScreen(
				dataState = viewModel.dataState
			) {
				lifecycleScope.launch {
					viewModel.dataIntent.send(DataIntent.FetchData)
				}
			}
		}
	}

	private fun initViewModel() {
	}

	companion object {
		fun newInstance() = HomeFragment()
	}
}

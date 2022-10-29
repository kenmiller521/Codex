package com.zil.codex.features.home.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.zil.codex.R
import com.zil.codex.features.home.fragments.HomeFragment
import com.zil.codex.features.home.view_models.HomeActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
	private val viewModel by viewModels<HomeActivityViewModel>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_home)
		initViewModel()
		if (savedInstanceState == null) {
			supportFragmentManager.beginTransaction()
				.replace(R.id.container, HomeFragment.newInstance())
				.commitNow()
		}
		viewModel.activitytest()
	}

	fun initViewModel() {
		viewModel.testMute.observe(this, Observer {
			if(it != null) {
				Log.d("zxcv", "ACTIVITY MUTE :$it")
				viewModel.testMute.value = null
			}
		})
	}
}

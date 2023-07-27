package com.theapache64.lottiebenchmark.ui.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theapache64.lottiebenchmark.BuildConfig
import com.theapache64.lottiebenchmark.util.flow.mutableEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {
    private val _versionName = MutableStateFlow("v${BuildConfig.VERSION_NAME}")
    val versionName: StateFlow<String> = _versionName
}
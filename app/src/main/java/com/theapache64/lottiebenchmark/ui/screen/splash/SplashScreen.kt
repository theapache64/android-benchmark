package com.theapache64.lottiebenchmark.ui.screen.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.theapache64.lottiebenchmark.R

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel(),
    onSplashFinished: () -> Unit,
) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottielogo))
        val progress by animateLottieCompositionAsState(composition)
        LaunchedEffect(Unit) {
            snapshotFlow { progress }
                .collect {
                    if (it == 1.0f) {
                        onSplashFinished()
                    }
                }
        }
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier.fillMaxSize()
        )

        // Version number
        val versionName by viewModel.versionName.collectAsState()
        Text(
            text = versionName,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp)
        )
    }
}
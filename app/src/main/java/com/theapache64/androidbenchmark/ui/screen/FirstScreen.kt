package com.theapache64.androidbenchmark.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.theapache64.androidbenchmark.R

const val ACTION_CLICK_RENDER = "Render It!"
const val LABEL_FINISHED_RENDERING = "FINISHED RENDERING!"

@Composable
fun FirstScreen(
    vectorType: LottieType,
) {

    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {

        val resId = when (vectorType) {
            LottieType.SMALL -> R.raw.small
            LottieType.MEDIUM -> TODO()
            LottieType.LARGE -> TODO()
        }

        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(resId))
        LottieAnimation(composition)
    }
}
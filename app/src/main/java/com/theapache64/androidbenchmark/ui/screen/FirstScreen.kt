package com.theapache64.androidbenchmark.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.theapache64.androidbenchmark.R

const val TAG_DONE = "tag_done"

@Composable
fun FirstScreen(
    vectorType: LottieType,
) {

    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {

        val resId = when (vectorType) {
            LottieType.SMALL -> R.raw.small
            LottieType.MEDIUM -> R.raw.medium
            LottieType.LARGE -> R.raw.large
        }

        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(resId))
        val progress by animateLottieCompositionAsState(composition)

        Column {
            LottieAnimation(
                composition = composition,
                progress = progress,
            )

            if (progress == 1.0f) {
                Text(
                    text = "DONE",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .testTag(TAG_DONE),
                    fontSize = 20.sp
                )
            }
        }
    }
}
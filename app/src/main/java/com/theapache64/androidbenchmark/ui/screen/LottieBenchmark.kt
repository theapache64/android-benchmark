package com.theapache64.androidbenchmark.ui.screen

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.github.theapache64.commonkeys.LottieKeys
import com.github.theapache64.commonkeys.LottieKeys.TAG_DONE
import com.theapache64.androidbenchmark.R


@Composable
fun LottieBenchmark(lottieType: LottieKeys.Type) {



    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {

        val resId = when (lottieType) {
            LottieKeys.Type.SMALL -> R.raw.small
            LottieKeys.Type.MEDIUM -> R.raw.medium
            LottieKeys.Type.LARGE -> R.raw.large
            LottieKeys.Type.SUPER_LARGE -> R.raw.super_large
        }

        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(resId))
        val progress by animateLottieCompositionAsState(composition)

        Column {
            LottieAnimation(
                composition = composition,
                progress = progress,
                modifier = Modifier.size(300.dp)
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
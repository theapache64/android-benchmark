package com.theapache64.androidbenchmark.benchmarker

import android.content.Intent
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import com.theapache64.androidbenchmark.ui.screen.LottieType
import com.theapache64.androidbenchmark.ui.screen.MainActivity
import com.theapache64.androidbenchmark.ui.screen.TAG_DONE
import kotlin.time.Duration.Companion.seconds

fun MacrobenchmarkRule.launchWith(lottieType : LottieType) = measureRepeated(
    packageName = "com.theapache64.androidbenchmark",
    metrics = listOf(
        FrameTimingMetric(),
        StartupTimingMetric()
    ),
    iterations = 10,
    startupMode = StartupMode.COLD,
    setupBlock = {
        // Press home button before each run to ensure the starting activity isn't visible.
        pressHome()

    }
) {

    startActivityAndWait(
        Intent()
            .putExtra(
                MainActivity.KEY_LOTTIE_TYPE,
                lottieType.name
            ).setAction("com.theapache64.androidbenchmark.MainActivity")
    )

    // starts default launch activity
    waitForUiElement(
        testTag = TAG_DONE,
        onFound = { tag ->
            // do nothing
        },
        onTimeout = {
            errorNotFound()
        },
        timeout = 20.seconds
    )
}
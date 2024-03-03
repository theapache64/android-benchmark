package com.theapache64.androidbenchmark.benchmarker

import android.content.Intent
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

fun MacrobenchmarkRule.launchWith(
    lottieType : String,
    timeout : Duration = 20.seconds
) = measureRepeated(
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
                "lottie_type",
                lottieType
            ).setAction("com.theapache64.androidbenchmark.MainActivity")
    )

    // starts default launch activity
    waitForUiElement(
        testTag = "tag_done",
        onFound = { tag ->
            // do nothing
        },
        onTimeout = {
            errorNotFound()
        },
        timeout = timeout
    )
}
package com.theapache64.androidbenchmark.benchmarker

import android.content.Intent
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import com.theapache64.androidbenchmark.ui.screen.MainActivity


fun MacrobenchmarkRule.launchWith(noOfInterceptors : Int) = measureRepeated(
    packageName = "com.theapache64.androidbenchmark",
    metrics = listOf(
        FrameTimingMetric()
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
                MainActivity.KEY_NO_OF_INTERCEPTORS,
                noOfInterceptors
            ).setAction("com.theapache64.androidbenchmark.MainActivity")
    )
}
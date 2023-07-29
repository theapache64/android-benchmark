package com.theapache64.androidbenchmark.benchmarker

import android.content.Intent
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import com.theapache64.androidbenchmark.ui.screen.MainActivity
import com.theapache64.androidbenchmark.ui.screen.RenderAction

fun MacrobenchmarkRule.launchWith(renderAction: RenderAction) = measureRepeated(
    packageName = "com.theapache64.androidbenchmark",
    metrics = listOf(
        FrameTimingMetric()
    ),
    iterations = 15,
    startupMode = StartupMode.COLD,
    setupBlock = {
        // Press home button before each run to ensure the starting activity isn't visible.
        pressHome()
    }
) {
    // starts default launch activity
    startActivityAndWait(
        Intent()
            .putExtra(
                MainActivity.KEY_RENDER_ACTION,
                renderAction
            ).setAction("com.theapache64.androidbenchmark.MainActivity")
    )


    /*// wait for "Hello World"
    device.wait(
        Until.hasObject(By.res(TEST_TAG)),
        10_000
    )
    val uiElement = device.findObject(By.res(TEST_TAG))
    require(uiElement != null) {
        "uiElement is null for `$TEST_TAG`"
    }*/
}
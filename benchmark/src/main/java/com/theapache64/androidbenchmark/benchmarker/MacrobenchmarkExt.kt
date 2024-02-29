package com.theapache64.androidbenchmark.benchmarker

import android.content.Intent
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import com.theapache64.androidbenchmark.ui.screen.ACTION_CLICK_RENDER
import com.theapache64.androidbenchmark.ui.screen.LABEL_FINISHED_RENDERING
import com.theapache64.androidbenchmark.ui.screen.MainActivity
import com.theapache64.androidbenchmark.ui.screen.RenderAction
import kotlin.time.Duration.Companion.seconds

fun MacrobenchmarkRule.launchWith(renderAction: RenderAction) = measureRepeated(
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
                MainActivity.KEY_RENDER_ACTION_NAME,
                renderAction.name
            ).setAction("com.theapache64.androidbenchmark.MainActivity")
    )

    // starts default launch activity
    /*waitForUiElementByText(
        text = ACTION_CLICK_RENDER,
        onFound = { button ->
            button.click()

            waitForUiElementByText(
                text = LABEL_FINISHED_RENDERING,
                onFound = {
                    println(":launchWith: rendering finished")
                },
                onTimeout = {
                    errorNotFound()
                },
                timeout = 20.seconds
            )
        },
        onTimeout = {
            errorNotFound()
        },
        timeout = 20.seconds
    )*/

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
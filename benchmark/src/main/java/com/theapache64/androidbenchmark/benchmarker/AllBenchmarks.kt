package com.theapache64.androidbenchmark.benchmarker

import android.content.Intent
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.theapache64.androidbenchmark.ui.screen.MainActivity
import com.theapache64.androidbenchmark.ui.screen.RenderAction
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class AllBenchmarks {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    companion object {
        private const val TEST_TAG = "label_hello_world"
    }

    @Test
    fun vectorCoil() = launchWith(RenderAction.VECTOR_COIL)

    @Test
    fun vectorCompose() = launchWith(RenderAction.VECTOR_COMPOSE)

    @Test
    fun pngCoil() = launchWith(RenderAction.PNG_COIL)

    @Test
    fun pngCompose() = launchWith(RenderAction.PNG_COMPOSE)

    @Test
    fun webpCoil() = launchWith(RenderAction.WEBP_COIL)

    @Test
    fun webpCompose() = launchWith(RenderAction.WEBP_COMPOSE)

    fun launchWith(renderAction: RenderAction) = benchmarkRule.measureRepeated(
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
}

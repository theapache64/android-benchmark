package com.theapache64.androidbenchmark.benchmarker

import android.content.Intent
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import com.theapache64.androidbenchmark.ui.screen.MainActivity
import com.theapache64.androidbenchmark.ui.screen.RenderAction
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class AndroidBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    companion object {
        private const val TEST_TAG = "label_hello_world"
    }

    @Test
    fun vectorCoil() = benchmarkRule.measureRepeated(
        packageName = "com.theapache64.androidbenchmark",
        metrics = listOf(
            FrameTimingMetric()
        ),
        iterations = 5,
        startupMode = StartupMode.COLD,
        setupBlock = {
            // Press home button before each run to ensure the starting activity isn't visible.
            pressHome()
        }
    ) {
        // starts default launch activity
        startActivityAndWait(
            Intent().putExtra(
                MainActivity.KEY_RENDER_ACTION,
                RenderAction.VECTOR_COIL
            )
        )

        // wait for "Hello World"
        device.wait(
            Until.hasObject(By.res(TEST_TAG)),
            10_000
        )
        val uiElement = device.findObject(By.res(TEST_TAG))
        require(uiElement != null) {
            "uiElement is null for `$TEST_TAG`"
        }
    }
}

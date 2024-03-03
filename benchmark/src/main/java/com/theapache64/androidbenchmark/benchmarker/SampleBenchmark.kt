package com.theapache64.androidbenchmark.benchmarker

import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class SampleBenchmark {

    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    companion object {
        private const val TEST_TAG = "label_hello_world"
    }

    @Test
    fun startup() = benchmarkRule.measureRepeated(
        packageName = "com.theapache64.androidbenchmark",
        metrics = listOf(
            FrameTimingMetric(),
            StartupTimingMetric()
        ),
        iterations = 5,
        startupMode = StartupMode.COLD,
        setupBlock = {
            // Press home button before each run to ensure the starting activity isn't visible.
            pressHome()


        }
    ) {
        // starts default launch activity
        startActivityAndWait()
    }
}
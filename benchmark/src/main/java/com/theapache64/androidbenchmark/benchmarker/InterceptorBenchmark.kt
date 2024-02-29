package com.theapache64.androidbenchmark.benchmarker

import android.content.Intent
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import com.theapache64.androidbenchmark.ui.screen.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

// I want to work with interceptors because in real applications, we typically use about 15 interceptors for tasks like caching, compression, handling user IDs, multiple DNS, response delays, and more.

@LargeTest
@RunWith(AndroidJUnit4::class)
class InterceptorBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    companion object {
        private const val TEST_TAG = "label_hello_world"
    }

    @Test fun withoutInterceptor() = benchmarkRule.launchWith(0)
    @Test fun oneInterceptor() = benchmarkRule.launchWith(1)
}
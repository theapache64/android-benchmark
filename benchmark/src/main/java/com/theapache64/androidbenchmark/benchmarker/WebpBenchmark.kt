package com.theapache64.androidbenchmark.benchmarker

import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.theapache64.androidbenchmark.ui.screen.VectorType
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class WebpBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun webpCoil() = benchmarkRule.launchWith(VectorType.WEBP_COIL)

    @Test
    fun webpCompose() = benchmarkRule.launchWith(VectorType.WEBP_COMPOSE)

}

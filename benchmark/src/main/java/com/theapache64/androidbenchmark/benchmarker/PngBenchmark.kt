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
class PngBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun pngCoil() = benchmarkRule.launchWith(VectorType.PNG_COIL)

    @Test
    fun pngCompose() = benchmarkRule.launchWith(VectorType.PNG_COMPOSE)

}

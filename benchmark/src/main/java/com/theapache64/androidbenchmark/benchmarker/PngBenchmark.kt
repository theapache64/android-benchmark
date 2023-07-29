package com.theapache64.androidbenchmark.benchmarker

import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.theapache64.androidbenchmark.ui.screen.RenderAction
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class PngBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun pngCoil() = benchmarkRule.launchWith(RenderAction.PNG_COIL)

    @Test
    fun pngCompose() = benchmarkRule.launchWith(RenderAction.PNG_COMPOSE)

}

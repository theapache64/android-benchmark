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

@RunWith(AndroidJUnit4::class)
class VectorBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun vectorCoil() = benchmarkRule.launchWith(RenderAction.VECTOR_COIL)
    @Test
    fun vectorCompose() = benchmarkRule.launchWith(RenderAction.VECTOR_COMPOSE)

}

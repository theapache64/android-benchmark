package com.theapache64.androidbenchmark.benchmarker

import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.theapache64.androidbenchmark.ui.screen.VectorType
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VectorBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun vectorCoil() = benchmarkRule.launchWith(VectorType.VECTOR_COIL)
    @Test
    fun vectorCompose() = benchmarkRule.launchWith(VectorType.VECTOR_COMPOSE)

}

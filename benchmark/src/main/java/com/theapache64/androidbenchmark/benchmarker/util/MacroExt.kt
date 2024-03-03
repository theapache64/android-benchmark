package com.theapache64.androidbenchmark.benchmarker.util

import android.content.Intent
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import com.github.theapache64.commonkeys.BenchmarkType
import com.github.theapache64.commonkeys.KEY_BENCHMARK_TYPE

 fun MacrobenchmarkRule.launchActivity(
    benchmarkType: BenchmarkType,
    data: Map<String, String>,
    measureBlock: MacrobenchmarkScope.() -> Unit
) = measureRepeated(
    packageName = "com.theapache64.androidbenchmark",
    metrics = listOf(
        FrameTimingMetric(),
        StartupTimingMetric()
    ),
    iterations = 10,
    startupMode = StartupMode.COLD,
    setupBlock = {
        // Press home button before each run to ensure the starting activity isn't visible.
        pressHome()

    },
) {
    startActivityAndWait(
        Intent().apply {
            putExtra(KEY_BENCHMARK_TYPE, benchmarkType.name)
            for ((key, value) in data) {
                putExtra(key, value)
            }
        }.setAction("com.theapache64.androidbenchmark.MainActivity")
    )

    measureBlock()
}
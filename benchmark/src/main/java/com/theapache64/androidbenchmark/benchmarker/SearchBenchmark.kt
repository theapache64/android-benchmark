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
class SearchBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun searchNoResultVector() = benchmarkRule.launchWith(RenderAction.VECTOR_SEARCH)

    @Test
    fun searchNoResultWebp() = benchmarkRule.launchWith(RenderAction.WEBP_SEARCH)

    @Test
    fun searchNoResultPng() = benchmarkRule.launchWith(RenderAction.PNG_SEARCH)

}

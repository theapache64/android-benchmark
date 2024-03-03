package com.theapache64.androidbenchmark.benchmarker

import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.github.theapache64.commonkeys.BenchmarkType
import com.github.theapache64.commonkeys.LottieKeys
import com.github.theapache64.commonkeys.LottieVsRiveKeys
import com.theapache64.androidbenchmark.benchmarker.util.launchActivity
import com.theapache64.androidbenchmark.benchmarker.util.waitForUiElement
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

@LargeTest
@RunWith(AndroidJUnit4::class)
class LottievsRiveBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun lottie() = benchmarkRule.launchLottieVsRive(LottieVsRiveKeys.Type.Lottie)

    @Test
    fun rive() = benchmarkRule.launchLottieVsRive(LottieVsRiveKeys.Type.Rive)
}


private fun MacrobenchmarkRule.launchLottieVsRive(
    type : LottieVsRiveKeys.Type,
) = launchActivity(
    benchmarkType = BenchmarkType.LottieRendering,
    data = mapOf(
        LottieVsRiveKeys.KEY_TYPE to type.name
    )
) {
    // starts default launch activity
    waitForUiElement(
        testTag = LottieKeys.TAG_DONE,
        onFound = { tag ->
            // do nothing
        },
        onTimeout = {
            errorNotFound()
        },
    )
}
package com.theapache64.androidbenchmark.benchmarker

import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.github.theapache64.commonkeys.BenchmarkType
import com.github.theapache64.commonkeys.LottieKeys
import com.theapache64.androidbenchmark.benchmarker.util.launchActivity
import com.theapache64.androidbenchmark.benchmarker.util.waitForUiElement
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

@LargeTest
@RunWith(AndroidJUnit4::class)
class LottieBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun smallLottie() = benchmarkRule.launchLottieBenchmark(LottieKeys.Type.SMALL)

    @Test
    fun mediumLottie() = benchmarkRule.launchLottieBenchmark(LottieKeys.Type.MEDIUM)

    @Test
    fun largeLottie() = benchmarkRule.launchLottieBenchmark(LottieKeys.Type.LARGE)

    @Test
    fun superLargeLottie() = benchmarkRule.launchLottieBenchmark(LottieKeys.Type.SUPER_LARGE)
}

private fun MacrobenchmarkRule.launchLottieBenchmark(
    lottieType: LottieKeys.Type,
    timeout: Duration = 20.seconds
) = launchActivity(
    benchmarkType = BenchmarkType.LottieRendering,
    data = mapOf(
        LottieKeys.KEY_TYPE to lottieType.name
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
        timeout = timeout
    )
}
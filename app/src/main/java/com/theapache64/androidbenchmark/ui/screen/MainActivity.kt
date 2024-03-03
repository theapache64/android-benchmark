package com.theapache64.androidbenchmark.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import com.github.theapache64.commonkeys.BenchmarkType
import com.github.theapache64.commonkeys.KEY_BENCHMARK_TYPE
import com.theapache64.androidbenchmark.ui.theme.Android_BenchmarkTheme


class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val benchmarkTypeName = intent.getStringExtra(KEY_BENCHMARK_TYPE)
            ?: error("No benchmark type passed") // default action
        val benchmarkType = BenchmarkType.valueOf(benchmarkTypeName)

        setContent {
            Android_BenchmarkTheme {
                Surface(modifier = Modifier.semantics {
                    testTagsAsResourceId = true
                }) {
                    when (benchmarkType) {
                        BenchmarkType.LottieRendering -> {
                            LottieBenchmark()
                        }

                        BenchmarkType.LottieVsRive -> {
                            // TODO()
                        }
                    }
                }
            }
        }


    }
}
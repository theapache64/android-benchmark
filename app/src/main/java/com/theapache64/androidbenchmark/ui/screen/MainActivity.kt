package com.theapache64.androidbenchmark.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import com.theapache64.androidbenchmark.ui.theme.Android_BenchmarkTheme

enum class LottieType {
    SMALL,
    MEDIUM,
    LARGE,
    SUPER_LARGE
}

class MainActivity : ComponentActivity() {

    companion object {
        const val KEY_LOTTIE_TYPE = "lottie_type"
    }

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lottieTypeName = intent.getStringExtra(KEY_LOTTIE_TYPE)
            ?: error("No lottie type passed") // default action
        val vectorType = LottieType.valueOf(lottieTypeName)

        setContent {
            Android_BenchmarkTheme {
                Surface(modifier = Modifier.semantics {
                    testTagsAsResourceId = true
                }) {
                    AppNavigation(vectorType = vectorType)
                }
            }
        }
    }

    @Composable
    private fun AppNavigation(vectorType: LottieType) {
        FirstScreen(vectorType = vectorType)
    }

}
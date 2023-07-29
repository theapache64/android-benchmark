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
import coil.compose.AsyncImage
import com.theapache64.androidbenchmark.R
import com.theapache64.androidbenchmark.ui.theme.Android_BenchmarkTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Android_BenchmarkTheme {
                Surface(modifier = Modifier.semantics {
                    testTagsAsResourceId = true
                }) {
                    AppNavigation()
                }
            }
        }
    }

    @Composable
    private fun AppNavigation() {
        var isSplashFinished by remember { mutableStateOf(false) }
        if (isSplashFinished) {
            SecondScreen()
        } else {
            SplashScreen(onSplashFinished = {
                isSplashFinished = true
            })
        }
    }


}

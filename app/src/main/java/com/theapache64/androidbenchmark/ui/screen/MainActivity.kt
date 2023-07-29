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

enum class RenderAction {
    VECTOR_COIL,
    VECTOR_COMPOSE,
    PNG_COIL,
    PNG_COMPOSE,
    WEBP_COIL,
    WEBP_COMPOSE
}

class MainActivity : ComponentActivity() {

    companion object {
        const val KEY_RENDER_ACTION_NAME = "render_action_name"
    }

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val renderActionName = intent.getStringExtra(KEY_RENDER_ACTION_NAME)
            ?: error("No render action passed") // default action
        val renderAction = RenderAction.valueOf(renderActionName)

        println("MainActivity:onCreate: renderAction: $renderAction")

        setContent {
            Android_BenchmarkTheme {
                Surface(modifier = Modifier.semantics {
                    testTagsAsResourceId = true
                }) {
                    AppNavigation(renderAction = renderAction)
                }
            }
        }
    }

    @Composable
    private fun AppNavigation(renderAction: RenderAction) {
        var isFirstScreenFinished by remember { mutableStateOf(false) }
        if (isFirstScreenFinished) {
            SecondScreen()
        } else {
            FirstScreen(
                renderAction = renderAction,
                onFirstScreenFinished = {
                    isFirstScreenFinished = true
                }
            )
        }
    }

}

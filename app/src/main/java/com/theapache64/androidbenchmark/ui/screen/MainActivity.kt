package com.theapache64.androidbenchmark.ui.screen

import android.content.Context
import android.content.Intent
import android.os.Build
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
import java.io.Serializable

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
        const val KEY_RENDER_ACTION = "render_action"
        fun createIntent(context: Context, renderAction: RenderAction): Intent {
            return Intent(context, MainActivity::class.java).apply {
                putExtra(KEY_RENDER_ACTION, renderAction)
            }
        }
    }

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val renderAction = intent.serializable(KEY_RENDER_ACTION)
            ?: RenderAction.PNG_COIL // default action

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

    inline fun <reified T : Serializable> Intent.serializable(key: String): T? = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getSerializableExtra(key, T::class.java)
        else -> @Suppress("DEPRECATION") getSerializableExtra(key) as? T
    }


}

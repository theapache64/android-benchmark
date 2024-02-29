package com.theapache64.androidbenchmark.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.unit.sp
import com.theapache64.androidbenchmark.ui.BadInterceptor
import com.theapache64.androidbenchmark.ui.theme.Android_BenchmarkTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

class MainActivity : ComponentActivity() {

    companion object {
        const val KEY_NO_OF_INTERCEPTORS = "no_of_interceptors"
    }

    private var noOfInterceptor: Int = -1
    private lateinit var okHttpClient: OkHttpClient

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        noOfInterceptor = intent.getIntExtra(KEY_NO_OF_INTERCEPTORS, -1)
        if (noOfInterceptor == -1) {
            throw IllegalArgumentException("No of interceptors not found")
        }

        // Create okhttp interceptor and attach our BadInterceptor
        okHttpClient = OkHttpClient.Builder()
            .apply {
                repeat(noOfInterceptor) {
                    addInterceptor(BadInterceptor())
                }
            }
            .build()

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

        var data by remember { mutableStateOf<String?>(null) }
        var isNetworkCallInProgress by remember { mutableStateOf(false) }
        LaunchedEffect(isNetworkCallInProgress) {
            withContext(Dispatchers.IO) {
                if (isNetworkCallInProgress) {
                    val startTime = System.currentTimeMillis()
                    val request = Request.Builder()
                        .url("http://colormind.io/list?time=${System.currentTimeMillis()}")
                        .build()

                    data = okHttpClient.newCall(request).execute().body.toString()
                    println("MainActivity:network call : iCount($noOfInterceptor), Time took  ${System.currentTimeMillis() - startTime}ms")
                    isNetworkCallInProgress = false
                }
            }
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column {
                Button(
                    onClick = {
                        isNetworkCallInProgress = true
                    },
                    modifier = Modifier.testTag("button_tag")
                ) {
                    Text(text = "START NETWORK CALL")
                }

                data?.let {
                    Text(
                        text = it,
                        fontSize = 40.sp,
                        modifier = Modifier.testTag("result_tag")
                    )
                }
            }
        }
    }
}

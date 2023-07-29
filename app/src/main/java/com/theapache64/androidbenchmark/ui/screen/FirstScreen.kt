package com.theapache64.androidbenchmark.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.theapache64.androidbenchmark.R
import kotlin.system.measureTimeMillis

@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CoilSvg()
    }
}

// Phase - 1
// SVG - Coil
@Composable
fun CoilSvg() {
    Column {
        measureTimeMillis {
            AsyncImage(
                model = R.drawable.mexicano_vector,
                modifier = Modifier.size(300.dp, 300.dp),
                contentDescription = null,
            )
        }.let {
            println(":CoilSvg: took $it ms")
        }

        measureTimeMillis {
            Image(
                painter = painterResource(id = R.drawable.mexicano_vector),
                modifier = Modifier.size(300.dp, 300.dp),
                contentDescription = null,
            )
        }.let {
            println(":Image: took $it ms")
        }
    }
}
// SVG - Compose

// PNG - Coil
// PNG - Compose

// Phase - 2
// WebP - Coil
// WebP - Compose
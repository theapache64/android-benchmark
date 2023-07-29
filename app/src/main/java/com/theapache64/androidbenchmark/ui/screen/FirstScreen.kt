package com.theapache64.androidbenchmark.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.theapache64.androidbenchmark.R

@Composable
fun FirstScreen(
    renderAction: RenderAction,
    onFirstScreenFinished: () -> Unit, // TODO:
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (renderAction) {
            RenderAction.VECTOR_COIL -> VectorCoil()
            RenderAction.VECTOR_COMPOSE -> VectorCompose()
            RenderAction.PNG_COIL -> PngCoil()
            RenderAction.PNG_COMPOSE -> PngCoil()
            RenderAction.WEBP_COIL -> WebPCoil()
            RenderAction.WEBP_COMPOSE -> WebPCompose()
        }
    }
}

// Phase - 1
// Vector - Coil
@Composable
fun VectorCoil() {
    AsyncImage(
        model = R.drawable.mexicano_vector,
        modifier = Modifier.size(300.dp, 300.dp),
        contentDescription = null,
    )
}

// Vector - Compose
@Composable
fun VectorCompose() {
    Image(
        painter = painterResource(id = R.drawable.mexicano_vector),
        modifier = Modifier.size(300.dp, 300.dp),
        contentDescription = null,
    )
}

// PNG - Coil
@Composable
fun PngCoil() {
    AsyncImage(
        model = R.drawable.mexicano_webp,
        modifier = Modifier.size(300.dp, 300.dp),
        contentDescription = null,
    )
}

// PNG - Compose
@Composable
fun PngCompose() {
    Image(
        painter = painterResource(id = R.drawable.mexicano_webp),
        modifier = Modifier.size(300.dp, 300.dp),
        contentDescription = null,
    )
}

// Phase - 2
// WebP - Coil
@Composable
fun WebPCoil() {
    AsyncImage(
        model = R.drawable.mexicano_webp,
        modifier = Modifier.size(300.dp, 300.dp),
        contentDescription = null,
    )
}

// WebP - Compose
@Composable
fun WebPCompose() {
    Image(
        painter = painterResource(id = R.drawable.mexicano_webp),
        modifier = Modifier.size(300.dp, 300.dp),
        contentDescription = null,
    )
}

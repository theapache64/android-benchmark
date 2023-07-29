package com.theapache64.androidbenchmark.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.theapache64.androidbenchmark.R

const val ACTION_CLICK_RENDER = "Render It!"
const val LABEL_FINISHED_RENDERING = "FINISHED RENDERING!"

@Composable
fun FirstScreen(
    renderAction: RenderAction,
    onFirstScreenFinished: () -> Unit, // TODO:
) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        var isRenderClicked by remember { mutableStateOf(false) }
        var isFinishedRendering by remember { mutableStateOf(false) }

        if (!isRenderClicked) {
            Button(
                onClick = {
                    isRenderClicked = true
                }
            ) {
                Text(text = ACTION_CLICK_RENDER)
            }
        }

        Column {
            if (isRenderClicked) {
                val callback = {
                    isFinishedRendering = true
                }
                when (renderAction) {
                    RenderAction.VECTOR_COIL -> VectorCoil(callback)
                    RenderAction.VECTOR_COMPOSE -> VectorCompose(callback)
                    RenderAction.PNG_COIL -> PngCoil(callback)
                    RenderAction.PNG_COMPOSE -> PngCompose(callback)
                    RenderAction.WEBP_COIL -> WebPCoil(callback)
                    RenderAction.WEBP_COMPOSE -> WebPCompose(callback)
                }
            }

            if (isFinishedRendering) {
                Text(text = LABEL_FINISHED_RENDERING)
            }

        }
    }
}

// Phase - 1
// Vector - Coil
@Composable
fun VectorCoil(
    isRenderingFinished: () -> Unit,
) {
    AsyncImage(
        model = R.drawable.lion_vector,
        modifier = Modifier.size(300.dp, 300.dp),
        contentDescription = null,
        onSuccess = {
            isRenderingFinished()
        }
    )
}

// Vector - Compose
@Composable
fun VectorCompose(
    isRenderingFinished: () -> Unit,
) {
    Image(
        painter = painterResource(id = R.drawable.lion_vector),
        modifier = Modifier.size(300.dp, 300.dp),
        contentDescription = null,
    )
    isRenderingFinished()
}

// PNG - Coil
@Composable
fun PngCoil(
    isRenderingFinished: () -> Unit,
) {
    AsyncImage(
        model = R.drawable.lion_webp,
        modifier = Modifier.size(300.dp, 300.dp),
        contentDescription = null,
        onSuccess = {
            isRenderingFinished()
        }
    )
}

// PNG - Compose
@Composable
fun PngCompose(
    isRenderingFinished: () -> Unit,
) {
    println(":PngCompose: composed!")
    Image(
        painter = painterResource(id = R.drawable.lion_webp),
        modifier = Modifier.size(300.dp, 300.dp),
        contentDescription = null,
    )
    isRenderingFinished()
}

// Phase - 2
// WebP - Coil
@Composable
fun WebPCoil(
    isRenderingFinished: () -> Unit,
) {
    AsyncImage(
        model = R.drawable.lion_webp,
        modifier = Modifier.size(300.dp, 300.dp),
        contentDescription = null,
        onSuccess = {
            isRenderingFinished()
        }
    )
}

// WebP - Compose
@Composable
fun WebPCompose(
    isRenderingFinished: () -> Unit,
) {
    Image(
        painter = painterResource(id = R.drawable.lion_webp),
        modifier = Modifier.size(300.dp, 300.dp),
        contentDescription = null,
    )
    isRenderingFinished()
}

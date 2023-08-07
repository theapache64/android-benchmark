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

const val ACTION_CLICK_RENDER = "Render It!"
const val LABEL_FINISHED_RENDERING = "FINISHED RENDERING!"

@Composable
fun FirstScreen(
    renderAction: RenderAction,
    onFirstScreenFinished: () -> Unit, // TODO:
) {

    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {

        when (renderAction) {
            RenderAction.VECTOR_COIL -> VectorCoil()
            RenderAction.VECTOR_COMPOSE -> VectorCompose()
            RenderAction.PNG_COIL -> PngCoil()
            RenderAction.PNG_COMPOSE -> PngCompose()
            RenderAction.WEBP_COIL -> WebPCoil()
            RenderAction.WEBP_COMPOSE -> WebPCompose()
        }

        /*var isRenderClicked by remember { mutableStateOf(false) }
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

        }*/
    }
}

// Phase - 1
// Vector - Coil
@Composable
fun VectorCoil(
) {
    AsyncImage(
        model = R.drawable.lion_vector,
        modifier = Modifier.size(300.dp, 300.dp),
        contentDescription = null,
    )
}

// Vector - Compose
@Composable
fun VectorCompose(
) {
    Image(
        painter = painterResource(id = R.drawable.lion_vector),
        modifier = Modifier.size(300.dp, 300.dp),
        contentDescription = null,
    )
}

// PNG - Coil
@Composable
fun PngCoil(
) {
    AsyncImage(
        model = R.drawable.lion_png,
        modifier = Modifier.size(300.dp, 300.dp),
        contentDescription = null,
    )
}

// PNG - Compose
@Composable
fun PngCompose(
) {
    println(":PngCompose: composed!")
    Image(
        painter = painterResource(id = R.drawable.lion_png),
        modifier = Modifier.size(300.dp, 300.dp),
        contentDescription = null,
    )
}

// Phase - 2
// WebP - Coil
@Composable
fun WebPCoil(
) {
    AsyncImage(
        model = R.drawable.lion_webp,
        modifier = Modifier.size(300.dp, 300.dp),
        contentDescription = null,
    )
}

// WebP - Compose
@Composable
fun WebPCompose(
) {
    Image(
        painter = painterResource(id = R.drawable.lion_webp),
        modifier = Modifier.size(300.dp, 300.dp),
        contentDescription = null,
    )
}

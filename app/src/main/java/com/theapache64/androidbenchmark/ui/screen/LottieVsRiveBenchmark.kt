package com.theapache64.androidbenchmark.ui.screen

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import app.rive.runtime.kotlin.RiveAnimationView
import app.rive.runtime.kotlin.controllers.RiveFileController
import app.rive.runtime.kotlin.core.Alignment
import app.rive.runtime.kotlin.core.Fit
import app.rive.runtime.kotlin.core.Loop
import app.rive.runtime.kotlin.core.PlayableInstance
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.github.theapache64.commonkeys.LottieVsRiveKeys
import com.theapache64.androidbenchmark.R

@Composable
fun LottieVsRiveBenchmark(renderType: LottieVsRiveKeys.Type) {
    println("QuickTag: :LottieVsRiveBenchmark: ")
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.progress_lottie)
    )
    val progress by animateLottieCompositionAsState(composition)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ){

        Column {
            when(renderType){
                LottieVsRiveKeys.Type.Lottie -> {
                    LottieAnimation(
                        composition = composition,
                        progress = { progress },
                        modifier = Modifier.size(300.dp)
                    )
                }
                LottieVsRiveKeys.Type.Rive -> {
                    RiveAnimation(
                        modifier = Modifier.size(300.dp),
                        resId = R.raw.progress_rive,
                        autoplay = true,
                        fit = Fit.FILL,
                        alignment = Alignment.CENTER,
                        loop = Loop.ONESHOT
                    )
                }
            }
        }
    }
}

@Suppress("LongMethod")
@Composable
fun RiveAnimation(
    modifier: Modifier = Modifier,
    @RawRes resId: Int,
    autoplay: Boolean = true,
    artboardName: String? = null,
    animationName: String? = null,
    stateMachineName: String? = null,
    fit: Fit = Fit.CONTAIN,
    alignment: Alignment = Alignment.CENTER,
    loop: Loop = Loop.AUTO,
    contentDescription: String? = null,
    notifyLoop: ((PlayableInstance) -> Unit)? = null,
    notifyPause: ((PlayableInstance) -> Unit)? = null,
    notifyPlay: ((PlayableInstance) -> Unit)? = null,
    notifyStateChanged: ((String, String) -> Unit)? = null,
    notifyStop: ((PlayableInstance) -> Unit)? = null,
    update: (RiveAnimationView) -> Unit = { _ -> }
) {

    var riveAnimationView: RiveAnimationView? = null
    var listener: RiveFileController.Listener? = null
    val lifecycleOwner = LocalLifecycleOwner.current

    val semantics = if (contentDescription != null) {
        Modifier.semantics {
            this.contentDescription = contentDescription
            this.role = Image
        }
    } else {
        Modifier
    }
    listener = object : RiveFileController.Listener {
        override fun notifyLoop(animation: PlayableInstance) {
            notifyLoop?.invoke(animation)
        }

        override fun notifyPause(animation: PlayableInstance) {
            notifyPause?.invoke(animation)
        }

        override fun notifyPlay(animation: PlayableInstance) {
            notifyPlay?.invoke(animation)
        }

        override fun notifyStateChanged(
            stateMachineName: String,
            stateName: String
        ) {
            notifyStateChanged?.invoke(stateMachineName, stateName)
        }

        override fun notifyStop(animation: PlayableInstance) {
            notifyStop?.invoke(animation)
        }
    }.takeIf {
        (notifyLoop != null) || (notifyPause != null) ||
                (notifyPlay != null) || (notifyStateChanged != null) ||
                (notifyStop != null)
    }

    AndroidView(
        modifier = modifier
            .then(semantics)
            .clipToBounds(),
        factory = { context ->
            riveAnimationView = RiveAnimationView(context).apply {
                setRiveResource(
                    resId,
                    artboardName,
                    animationName,
                    stateMachineName,
                    autoplay,
                    fit,
                    alignment,
                    loop
                )
            }
            listener?.let {
                riveAnimationView?.registerListener(it)
            }
            riveAnimationView!!
        },
        update = {
            update.invoke(it)
        }
    )

    DisposableEffect(lifecycleOwner) {
        onDispose {
            listener?.let {
                riveAnimationView?.unregisterListener(it)
            }
        }
    }
}

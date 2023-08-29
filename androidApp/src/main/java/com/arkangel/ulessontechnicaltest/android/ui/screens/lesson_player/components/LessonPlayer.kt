package com.arkangel.ulessontechnicaltest.android.ui.screens.lesson_player.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.Player
import androidx.media3.ui.PlayerView
import androidx.media3.ui.PlayerView.SHOW_BUFFERING_ALWAYS

@Composable
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
fun LessonPlayer(player: Player) {
    val context = LocalContext.current
    DisposableEffect(
        AndroidView(
            factory = {
                PlayerView(context).apply {
                    hideController()
                    setPlayer(player)
                    setShowPreviousButton(false)
                    setShowNextButton(false)
                    setShowBuffering(SHOW_BUFFERING_ALWAYS)
                    setShowShuffleButton(false)
                    setShowVrButton(false)
                }
            }
        )
    ) {
        onDispose { player.release() }
    }
}

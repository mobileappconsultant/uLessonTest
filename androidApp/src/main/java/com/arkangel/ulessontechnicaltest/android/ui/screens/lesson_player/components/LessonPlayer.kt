package com.arkangel.ulessontechnicaltest.android.ui.screens.lesson_player.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.Player
import androidx.media3.ui.PlayerView
import androidx.media3.ui.PlayerView.SHOW_BUFFERING_ALWAYS
import com.arkangel.ulessontechnicaltest.features.chapter.model.Lesson

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
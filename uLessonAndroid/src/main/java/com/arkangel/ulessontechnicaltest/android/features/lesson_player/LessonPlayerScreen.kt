package com.arkangel.ulessontechnicaltest.android.features.lesson_player

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.AlertDialog
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.unit.dp
import com.arkangel.ulessontechnicaltest.android.R
import com.arkangel.ulessontechnicaltest.android.features.lesson_player.components.BookmarkList
import com.arkangel.ulessontechnicaltest.android.features.lesson_player.components.LessonPlayer
import com.arkangel.ulessontechnicaltest.android.utils.LessonsWrapper
import com.arkangel.ulessontechnicaltest.android.utils.findActivity
import com.github.fengdai.compose.media.Media
import com.github.fengdai.compose.media.MediaState
import com.github.fengdai.compose.media.ShowBuffering
import com.github.fengdai.compose.media.rememberMediaState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

data class LessonPlayerScreenNavArgs(
    val lessons: LessonsWrapper,
    val index: Int
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Destination(
    navArgsDelegate = LessonPlayerScreenNavArgs::class
)
@Composable
fun LessonPlayerScreen(navigator: DestinationsNavigator) {
    val viewModel: LessonPlayerScreenViewModel = koinViewModel()
    var loading by remember { viewModel.loading }
    var showBookmarkDialog by remember { viewModel.showBookmarkDialog }
    var temporarilyPaused by remember { viewModel.temporarilyPaused }
    var dialogText by remember { mutableStateOf("") }
    val bookmarks = remember { viewModel.bookmarks }
    val currentLesson by remember { viewModel.currentLesson }

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.isStatusBarVisible = !isLandscape
        systemUiController.isNavigationBarVisible = !isLandscape
    }
    val context = LocalContext.current

    BackHandler(enabled = true) {
        viewModel.saveCurrentLesson()
        navigator.popBackStack()
    }

    val mediaState = rememberMediaState(viewModel.exoPlayer)
    val mediaContent = remember {
        movableContentOf { isLandscape: Boolean, modifier: Modifier ->
            MediaContent(mediaState, isLandscape, modifier)
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { navigator.popBackStack() }) {
                        androidx.compose.material3.Icon(Icons.Filled.ArrowBack, null)
                    }
                },
                modifier = Modifier
                    .semantics { testTagsAsResourceId = true }
                    .testTag("topAppBar")
            )
        },
        backgroundColor = MaterialTheme.colors.surface
    ) { padding ->
        if (!isLandscape) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                mediaContent(
                    false,
                    Modifier
                        .padding(padding)
                        .fillMaxWidth()
                        .aspectRatio(16f / 9f)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Icon(
                        modifier = Modifier.clickable {
                            showBookmarkDialog = true
                            temporarilyPaused = viewModel.exoPlayer.isPlaying
                            viewModel.exoPlayer.pause()
                        },
                        painter = painterResource(R.drawable.bookmark),
                        contentDescription = "Bookmark"
                    )
                }
                Text(
                    currentLesson.title, style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(12.dp)
                )

                BookmarkList(
                    modifier = Modifier.weight(1f),
                    bookmarks = bookmarks,
                    onSelectBookmark = { bookmark ->
                        viewModel.exoPlayer.seekTo(bookmark.timestamp)
                    },
                    onDeleteBookmark = { bookmark ->
                        viewModel.deleteBookmark(bookmark)
                    }
                )
            }
        }
    }

    if (isLandscape) {
        mediaContent(
            true,
            Modifier
                .fillMaxSize()
                .background(Color.Black)
        )
    }

    if (loading) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

    if (showBookmarkDialog) {
        AlertDialog(
            onDismissRequest = {
                showBookmarkDialog = false
                if (temporarilyPaused) {
                    viewModel.exoPlayer.play()
                    temporarilyPaused = false
                }
            },
            title = {
                Text(
                    text = "Add Bookmark",
                    style = MaterialTheme.typography.h6
                )
            },
            text = {
                Column {
                    TextField(
                        value = dialogText,
                        onValueChange = { dialogText = it },
                        maxLines = 4,
                        colors = TextFieldDefaults.textFieldColors(
                            errorIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                        ),
                    )
                }
            },
            buttons = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    TextButton(
                        onClick = {
                            showBookmarkDialog = false
                            if (temporarilyPaused) {
                                viewModel.exoPlayer.play()
                                temporarilyPaused = false
                            }
                        }
                    ) {
                        Text("Cancel")
                    }

                    Button(
                        onClick = {
                            if (dialogText.trim().isEmpty()) {
                                Toast.makeText(
                                    context,
                                    context.getString(R.string.please_enter_a_note), Toast.LENGTH_SHORT
                                ).show()
                                return@Button
                            }
                            viewModel.saveBookmark(dialogText.trim())

                            showBookmarkDialog = false
                            if (temporarilyPaused) {
                                viewModel.exoPlayer.play()
                                temporarilyPaused = false
                            }
                        }
                    ) {
                        Text("Okay")
                    }
                }
            },
        )
    }
}

@Composable
private fun MediaContent(
    mediaState: MediaState,
    isLandscape: Boolean,
    modifier: Modifier = Modifier
) {
    val activity = LocalContext.current.findActivity()!!
    val enterFullscreen = {
        activity.requestedOrientation =
            ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE
    }
    val exitFullscreen = {
        @SuppressLint("SourceLockedOrientationActivity")
        // Will reset to SCREEN_ORIENTATION_USER later
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT
    }
    Box(modifier) {
        Media(
            mediaState,
            modifier = Modifier.fillMaxSize(),
            showBuffering = ShowBuffering.Always,
            buffering = {
                Box(Modifier.fillMaxSize(), Alignment.Center) {
                    androidx.compose.material3.CircularProgressIndicator()
                }
            },
            controller = { LessonPlayer(mediaState, Modifier.fillMaxSize()) }
        )
        Button(
            modifier = Modifier.align(Alignment.BottomEnd),
            onClick = if (isLandscape) exitFullscreen else enterFullscreen
        ) {
            Icon(
                painter = painterResource(if (isLandscape) R.drawable.fullscreen_exit else R.drawable.fullscreen),
                contentDescription = "Fullscreen/Exit",
                tint = Color.White,
            )
        }
    }
    val onBackPressedCallback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                exitFullscreen()
            }
        }
    }
    val onBackPressedDispatcher = activity.onBackPressedDispatcher
    DisposableEffect(onBackPressedDispatcher) {
        onBackPressedDispatcher.addCallback(onBackPressedCallback)
        onDispose { onBackPressedCallback.remove() }
    }
    SideEffect {
        onBackPressedCallback.isEnabled = isLandscape
        if (isLandscape) {
            if (activity.requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_USER) {
                activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE
            }
        } else {
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_USER
        }
    }
}

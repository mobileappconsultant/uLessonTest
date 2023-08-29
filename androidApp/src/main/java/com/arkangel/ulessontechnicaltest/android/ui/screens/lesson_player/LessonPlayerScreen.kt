package com.arkangel.ulessontechnicaltest.android.ui.screens.lesson_player

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.arkangel.ulessontechnicaltest.android.R
import com.arkangel.ulessontechnicaltest.android.ui.screens.lesson_player.components.BookmarkList
import com.arkangel.ulessontechnicaltest.android.ui.screens.lesson_player.components.LessonPlayer
import com.arkangel.ulessontechnicaltest.android.utils.LessonsWrapper
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

data class LessonPlayerScreenNavArgs(
    val lessons: LessonsWrapper,
    val index: Int
)

@Destination(
    navArgsDelegate = LessonPlayerScreenNavArgs::class
)
@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun LessonPlayerScreen(navigator: DestinationsNavigator) {
    val viewModel: LessonPlayerScreenViewModel = koinViewModel()
    var loading by remember { viewModel.loading }
    var showBookmarkDialog by remember { viewModel.showBookmarkDialog }
    var temporarilyPaused by remember { viewModel.temporarilyPaused }
    var dialogText by remember { mutableStateOf("") }
    val bookmarks = remember { viewModel.bookmarks }
    val currentLesson by remember { viewModel.currentLesson }

    val context = LocalContext.current

    BackHandler(enabled = false) {
        viewModel.saveCurrentLesson()
        navigator.popBackStack()
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            LessonPlayer(player = viewModel.exoPlayer)
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
                    TextButton(onClick = {
                        showBookmarkDialog = false
                        if (temporarilyPaused) {
                            viewModel.exoPlayer.play()
                            temporarilyPaused = false
                        }
                    }) {
                        Text("Cancel")
                    }

                    Button(onClick = {
                        if (dialogText.trim().isEmpty()) {
                            Toast.makeText(context,
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

                    }) {
                        Text("Okay")
                    }
                }
            },
        )
    }
}
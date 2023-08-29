package com.arkangel.ulessontechnicaltest.android.ui.screens.lesson_player

import android.app.Application
import android.net.Uri
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.Player.MEDIA_ITEM_TRANSITION_REASON_AUTO
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.offline.DownloadRequest
import androidx.media3.exoplayer.source.ConcatenatingMediaSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import com.arkangel.ulessontechnicaltest.android.ui.screens.destinations.LessonPlayerScreenDestination
import com.arkangel.ulessontechnicaltest.android.utils.DownloadManager
import com.arkangel.ulessontechnicaltest.android.utils.PlayerUtil
import com.arkangel.ulessontechnicaltest.features.bookmark.model.Bookmark
import com.arkangel.ulessontechnicaltest.features.bookmark.usecase.AddBookmarkUseCase
import com.arkangel.ulessontechnicaltest.features.bookmark.usecase.DeleteBookmarkUseCase
import com.arkangel.ulessontechnicaltest.features.bookmark.usecase.GetBookmarksUseCase
import com.arkangel.ulessontechnicaltest.features.chapter.model.Lesson
import kotlinx.coroutines.launch

@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
class LessonPlayerScreenViewModel(
    state: SavedStateHandle,
    private val application: Application,
    private val addBookmarkUseCase: AddBookmarkUseCase,
    private val getBookmarksUseCase: GetBookmarksUseCase,
    private val deleteBookmarkUseCase: DeleteBookmarkUseCase,
    val exoPlayer: ExoPlayer,
    private val playerUtil: PlayerUtil,
    private val downloadManager: DownloadManager
) : ViewModel() {
    private val navArgs = LessonPlayerScreenDestination.argsFrom(state)

    private val lessons: List<Lesson>
        get() = navArgs.lessons.lessons

    val currentLesson = mutableStateOf(navArgs.lessons[navArgs.index])
    val currentLessonIndex = mutableIntStateOf(navArgs.index)

    val loading = mutableStateOf(false)
    val showBookmarkDialog = mutableStateOf(false)
    val temporarilyPaused = mutableStateOf(false)
    val bookmarks = mutableStateListOf<Bookmark>()

    init {
        fetchBookmarks()
        initializeMedia()
    }

    private fun fetchBookmarks() {
        bookmarks.clear()
        bookmarks.addAll(getBookmarksUseCase.getBookmarks(currentLesson.value))
    }

    private fun initializeMedia() {
        val mediaSources = navArgs.lessons.lessons.mapIndexed { index, lesson ->
            val uri = Uri.parse(lesson.videoUrl)
            if (index == navArgs.index || index == navArgs.index + 1 || index == navArgs.index - 1) {
                downloadManager.queueDownload(DownloadRequest.Builder(uri.toString(), uri).build())
            }

            ProgressiveMediaSource.Factory(playerUtil.getDataSourceFactory(application))
                .createMediaSource(MediaItem.fromUri(uri))
        }

        val source = ConcatenatingMediaSource(*mediaSources.toTypedArray())
        exoPlayer.setMediaSource(source)
        exoPlayer.prepare()
        exoPlayer.seekTo(currentLessonIndex.value, C.TIME_UNSET)
        exoPlayer.playWhenReady = true
        downloadManager.resumeAllDownloads()

        exoPlayer.addListener(object : Player.Listener {
            override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                super.onMediaItemTransition(mediaItem, reason)

                if (reason == MEDIA_ITEM_TRANSITION_REASON_AUTO) {
                    if (currentLessonIndex.value + 1 < lessons.size) {
                        currentLessonIndex.value += 1
                        currentLesson.value = lessons[currentLessonIndex.value]

                        // Fetch updated bookmarks
                        fetchBookmarks()

                        navArgs.lessons.lessons.forEachIndexed { index, lesson ->
                            val uri = Uri.parse(lesson.videoUrl)
                            if (index == navArgs.index || index == navArgs.index + 1 || index == navArgs.index - 1) {
                                downloadManager.queueDownload(DownloadRequest.Builder(uri.toString(), uri).build())
                            }
                        }
                    }
                }
            }
        })
    }

    fun saveBookmark(note: String) {
        val currentTime = exoPlayer.currentPosition
        val savedLesson = currentLesson.value
        viewModelScope.launch {
            addBookmarkUseCase.addBookmark(
                savedLesson,
                Bookmark(
                    note = note,
                    timestamp = currentTime
                )
            )
            fetchBookmarks()
        }
    }

    fun deleteBookmark(bookmark: Bookmark) {
        viewModelScope.launch {
            deleteBookmarkUseCase.deleteBookmark(currentLesson.value, bookmark)
            fetchBookmarks()
        }
    }

    fun saveCurrentLesson() {
    }
}

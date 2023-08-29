package com.arkangel.ulessontechnicaltest

import android.app.Application
import android.content.Context
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import com.arkangel.ulessontechnicaltest.android.features.subjects.usecase.GetSubjectsUseCase
import com.arkangel.ulessontechnicaltest.android.ui.screens.home.HomeScreenViewModel
import com.arkangel.ulessontechnicaltest.android.ui.screens.lesson_player.LessonPlayerScreenViewModel
import com.arkangel.ulessontechnicaltest.android.ui.screens.subject_info.SubjectInfoScreenViewModel
import com.arkangel.ulessontechnicaltest.android.utils.DownloadManager
import com.arkangel.ulessontechnicaltest.android.utils.DownloadManagerImpl
import com.arkangel.ulessontechnicaltest.android.utils.PlayerUtil
import com.arkangel.ulessontechnicaltest.features.bookmark.usecase.AddBookmarkUseCase
import com.arkangel.ulessontechnicaltest.features.bookmark.usecase.DeleteBookmarkUseCase
import com.arkangel.ulessontechnicaltest.features.bookmark.usecase.GetBookmarksUseCase
import com.arkangel.ulessontechnicaltest.features.chapter.usecase.GetChaptersUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.dsl.module

class ULessonTechnicalTestApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                module {
                    androidContext(this@ULessonTechnicalTestApplication)

                    // Android Resources
                    factory { get<Context>().resources }

                    // Use cases
                    factory { GetSubjectsUseCase(get()) }
                    factory { GetChaptersUseCase() }

                    // View Models
                    viewModelOf(::HomeScreenViewModel)
                    viewModelOf(::SubjectInfoScreenViewModel)
                    viewModelOf(::LessonPlayerScreenViewModel)

                    // Bookmarks
                    factory { AddBookmarkUseCase() }
                    factory { GetBookmarksUseCase() }
                    factory { DeleteBookmarkUseCase() }

                    // Player Util
                    single { PlayerUtil() }

                    // Download Manager
                    single <DownloadManager> { DownloadManagerImpl(get()) }

                    factory {
                        val playerUtil: PlayerUtil = get()
                        ExoPlayer.Builder(get())
                            .setMediaSourceFactory(
                                DefaultMediaSourceFactory(get<Context>()).setDataSourceFactory(playerUtil.getDataSourceFactory(get()))
                            ).build()
                    }
                }
            )
        }
    }
}

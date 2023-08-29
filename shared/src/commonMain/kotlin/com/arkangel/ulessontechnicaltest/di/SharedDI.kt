package com.arkangel.ulessontechnicaltest.di

import com.arkangel.ulessontechnicaltest.features.bookmark.BookmarkManager
import com.arkangel.ulessontechnicaltest.features.bookmark.BookmarkManagerImpl
import com.arkangel.ulessontechnicaltest.features.chapter.api.ChapterApi
import com.arkangel.ulessontechnicaltest.features.chapter.api.MockChapterApi
import com.arkangel.ulessontechnicaltest.features.chapter.repository.ChapterRepository
import com.arkangel.ulessontechnicaltest.features.chapter.repository.ChapterRepositoryImpl
import com.arkangel.ulessontechnicaltest.network.makeClient
import com.russhwolf.settings.Settings
import org.koin.core.KoinApplication
import org.koin.dsl.module

internal val sharedDI: KoinApplication by lazy {
    KoinApplication.init().apply {
        modules(
            module {
                single { makeClient() }
                single { Settings() }

                // Chapter Api
                factory<ChapterApi> { MockChapterApi(get()) }

                // Chapter Repository
                factory<ChapterRepository> { ChapterRepositoryImpl(get()) }

                // Bookmarks
                single<BookmarkManager> { BookmarkManagerImpl(get()) }
            }
        )
    }
}
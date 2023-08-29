package com.arkangel.ulessontechnicaltest.features.bookmark.usecase

import com.arkangel.ulessontechnicaltest.di.sharedDI
import com.arkangel.ulessontechnicaltest.features.bookmark.BookmarkManager
import com.arkangel.ulessontechnicaltest.features.bookmark.model.Bookmark
import com.arkangel.ulessontechnicaltest.features.chapter.model.Lesson

class GetBookmarksUseCase {
    private val bookmarkManager: BookmarkManager by sharedDI.koin.inject()
    fun getBookmarks(lesson: Lesson): List<Bookmark> = bookmarkManager.getBookmarks(lesson)
}

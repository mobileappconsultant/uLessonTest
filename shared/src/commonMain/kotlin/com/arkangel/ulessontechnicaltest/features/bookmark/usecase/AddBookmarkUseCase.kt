package com.arkangel.ulessontechnicaltest.features.bookmark.usecase

import com.arkangel.ulessontechnicaltest.di.sharedDI
import com.arkangel.ulessontechnicaltest.features.bookmark.BookmarkManager
import com.arkangel.ulessontechnicaltest.features.bookmark.model.Bookmark
import com.arkangel.ulessontechnicaltest.features.chapter.model.Lesson

class AddBookmarkUseCase {
    private val bookmarkManager: BookmarkManager by sharedDI.koin.inject()
    fun addBookmark(lesson: Lesson, bookmark: Bookmark) = bookmarkManager.addBookmark(lesson, bookmark)
}

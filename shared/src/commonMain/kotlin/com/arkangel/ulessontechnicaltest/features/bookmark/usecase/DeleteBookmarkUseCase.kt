package com.arkangel.ulessontechnicaltest.features.bookmark.usecase

import com.arkangel.ulessontechnicaltest.di.sharedDI
import com.arkangel.ulessontechnicaltest.features.bookmark.BookmarkManager
import com.arkangel.ulessontechnicaltest.features.bookmark.model.Bookmark
import com.arkangel.ulessontechnicaltest.features.chapter.model.Lesson

class DeleteBookmarkUseCase {
    private val bookmarkManager: BookmarkManager by sharedDI.koin.inject()
    fun deleteBookmark(lesson: Lesson, bookmark: Bookmark) = bookmarkManager.removeBookmark(lesson, bookmark)
}
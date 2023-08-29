package com.arkangel.ulessontechnicaltest.features.bookmark

import com.arkangel.ulessontechnicaltest.features.bookmark.model.Bookmark
import com.arkangel.ulessontechnicaltest.features.chapter.model.Lesson

interface BookmarkManager {
    fun getBookmarks(lesson: Lesson): List<Bookmark>
    fun addBookmark(lesson: Lesson, bookmark: Bookmark)
    fun removeBookmark(lesson: Lesson, bookmark: Bookmark)
}

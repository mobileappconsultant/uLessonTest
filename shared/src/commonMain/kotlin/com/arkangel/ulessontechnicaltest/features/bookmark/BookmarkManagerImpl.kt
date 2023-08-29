package com.arkangel.ulessontechnicaltest.features.bookmark

import com.arkangel.ulessontechnicaltest.features.bookmark.model.Bookmark
import com.arkangel.ulessontechnicaltest.features.chapter.model.Lesson
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

private const val BOOKMARKS_KEY = "bookmarks"

internal class BookmarkManagerImpl(
    private val settings: Settings
): BookmarkManager {
    private val bookmarks = mutableMapOf<String, MutableList<Bookmark>>()
    init {
        val jsonString: String = settings[BOOKMARKS_KEY] ?: ""
        if (jsonString.isNotEmpty()) {
            val tmp: Map<String, MutableList<Bookmark>> = Json.decodeFromString(jsonString)
            bookmarks.putAll(tmp)
        }
    }

    override fun getBookmarks(lesson: Lesson): List<Bookmark> {
        return bookmarks[lesson.videoUrl] ?: listOf()
    }

    override fun addBookmark(lesson: Lesson, bookmark: Bookmark) {
        if (!bookmarks.containsKey(lesson.videoUrl)) {
            bookmarks[lesson.videoUrl] = mutableListOf()
        }
        bookmarks[lesson.videoUrl]?.add(bookmark)

        storeBookmarks()
    }

    override fun removeBookmark(lesson: Lesson, bookmark: Bookmark) {
        bookmarks[lesson.videoUrl]?.remove(bookmark)
        storeBookmarks()
    }

    private fun storeBookmarks() {
        val response = Json.encodeToString(bookmarks)
        settings[BOOKMARKS_KEY] = response
    }

}
package com.arkangel.ulessontechnicaltest.features.bookmark.model

import com.arkangel.ulessontechnicaltest.features.chapter.model.Lesson
import kotlinx.serialization.Serializable

@Serializable
data class Bookmark(
    val note: String,
    val timestamp: Long,
)

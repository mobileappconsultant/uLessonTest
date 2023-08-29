package com.arkangel.ulessontechnicaltest.features.bookmark.model

import kotlinx.serialization.Serializable

@Serializable
data class Bookmark(
    val note: String,
    val timestamp: Long,
)

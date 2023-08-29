package com.arkangel.ulessontechnicaltest.features.chapter.model

import kotlinx.serialization.Serializable

@Serializable
data class Lesson(
    val title: String,
    val videoUrl: String
)

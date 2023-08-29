package com.arkangel.ulessontechnicaltest.features.chapter.repository

import com.arkangel.ulessontechnicaltest.features.chapter.model.Chapter
import com.arkangel.ulessontechnicaltest.utils.ApiResponse

interface ChapterRepository {
    suspend fun getChapters(subject: String): ApiResponse<List<Chapter>>
}
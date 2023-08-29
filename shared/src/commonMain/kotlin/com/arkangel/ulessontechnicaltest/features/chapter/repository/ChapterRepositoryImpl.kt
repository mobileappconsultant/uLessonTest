package com.arkangel.ulessontechnicaltest.features.chapter.repository

import com.arkangel.ulessontechnicaltest.features.chapter.api.ChapterApi
import com.arkangel.ulessontechnicaltest.features.chapter.model.Chapter
import com.arkangel.ulessontechnicaltest.utils.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class ChapterRepositoryImpl(
    private val chapterApi: ChapterApi
) : ChapterRepository {
    override suspend fun getChapters(subject: String): ApiResponse<List<Chapter>> = withContext(Dispatchers.IO) {
        try {
            val chapters = chapterApi.getChapters(subject)
            ApiResponse(chapters)
        } catch (error: Throwable) {
            ApiResponse(null, error)
        }
    }
}

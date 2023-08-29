package com.arkangel.ulessontechnicaltest.features.chapter.api

import com.arkangel.ulessontechnicaltest.features.chapter.model.Chapter

interface ChapterApi {
    suspend fun getChapters(subject: String): List<Chapter>
}

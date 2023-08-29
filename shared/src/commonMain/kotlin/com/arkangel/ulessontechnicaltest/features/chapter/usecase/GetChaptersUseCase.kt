package com.arkangel.ulessontechnicaltest.features.chapter.usecase

import com.arkangel.ulessontechnicaltest.di.sharedDI
import com.arkangel.ulessontechnicaltest.features.chapter.model.Chapter
import com.arkangel.ulessontechnicaltest.features.chapter.repository.ChapterRepository
import com.arkangel.ulessontechnicaltest.utils.ApiResponse

class GetChaptersUseCase {
    private val chapterRepository: ChapterRepository by sharedDI.koin.inject()

    suspend fun getChapters(subject: String): ApiResponse<List<Chapter>> {
        return chapterRepository.getChapters(subject)
    }
}

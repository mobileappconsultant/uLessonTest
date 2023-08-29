package com.arkangel.ulessontechnicaltest.utils

import com.arkangel.ulessontechnicaltest.di.sharedDI
import com.arkangel.ulessontechnicaltest.features.chapter.model.Lesson
import com.russhwolf.settings.Settings
import com.russhwolf.settings.contains
import com.russhwolf.settings.get
import com.russhwolf.settings.set
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class LearningProgress(
    val lesson: Lesson,
    val timestamp: Long
)

private const val LESSON_PROGRESS_PREF = "lesson_progress"

class ResumeLearningUseCase {
    private val settings: Settings = sharedDI.koin.get()

    fun saveLessonProgress(lesson: Lesson, timestamp: Long) {
        val jsonString = Json.encodeToString(LearningProgress(
            lesson = lesson,
            timestamp = timestamp
        ))

        settings[LESSON_PROGRESS_PREF] = jsonString
    }
    fun getSavedProgress(): LearningProgress? {
        if (settings.contains(LESSON_PROGRESS_PREF)) {
            val jsonString: String = settings[LESSON_PROGRESS_PREF] ?: return null

            return Json.decodeFromString(jsonString)
        }

        return null
    }

    fun clearProgress() {
        settings.remove(LESSON_PROGRESS_PREF)
    }
}
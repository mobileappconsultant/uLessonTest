package com.arkangel.ulessontechnicaltest.android.utils

import com.arkangel.ulessonsharedlibrary.features.chapter.model.Lesson
import kotlinx.serialization.Serializable

@Serializable
data class LessonsWrapper(
    val lessons: List<Lesson>
) {
    operator fun get(index: Int): Lesson {
        return lessons[index]
    }
}

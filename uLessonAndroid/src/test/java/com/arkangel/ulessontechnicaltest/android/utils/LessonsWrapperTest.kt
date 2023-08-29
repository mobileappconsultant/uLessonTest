package com.arkangel.ulessontechnicaltest.android.utils

import com.arkangel.ulessonsharedlibrary.features.chapter.model.Lesson
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Test

class LessonsWrapperTest {

    @Test
    fun testSerializationAndDeserialization() {
        val lesson1 = Lesson("Math", "https//sample.com")
        val lesson2 = Lesson("Science", "https//sample.com")
        val lessonsWrapper = LessonsWrapper(listOf(lesson1, lesson2))
        val json = Json.encodeToString(lessonsWrapper)
        val deserializedWrapper = Json.decodeFromString<LessonsWrapper>(json)

        assertEquals(lessonsWrapper, deserializedWrapper)
    }

    @Test
    fun testGetOperator() {
        val lesson1 = Lesson("Math", "https//sample.com")
        val lesson2 = Lesson("Science", "https//sample.com")
        val lessonsWrapper = LessonsWrapper(listOf(lesson1, lesson2))

        assertEquals(lesson1, lessonsWrapper[0])
        assertEquals(lesson2, lessonsWrapper[1])
    }
}

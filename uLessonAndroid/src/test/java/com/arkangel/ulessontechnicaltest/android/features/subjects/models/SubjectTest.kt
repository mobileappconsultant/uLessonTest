package com.arkangel.ulessontechnicaltest.android.features.subjects.models

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Test

class SubjectTest {

    @Test
    fun testSerializationAndDeserialization() {
        val subject = Subject("Math", 123)
        val json = Json.encodeToString(subject)
        val deserializedSubject = Json.decodeFromString<Subject>(json)

        assertEquals(subject, deserializedSubject)
    }
}

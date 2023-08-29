package com.arkangel.ulessontechnicaltest.android

import org.junit.Assert.assertEquals
import org.junit.Test

class DestinationsTest {

    @Test
    fun testRoutes() {
        assertEquals("/splash", Destinations.Home.route)
        assertEquals("/subject", Destinations.SubjectInfo.route)
        assertEquals("/chapter", Destinations.ChapterInfo.route)
        assertEquals("/lesson-player", Destinations.LessonPlayer.route)
    }
}

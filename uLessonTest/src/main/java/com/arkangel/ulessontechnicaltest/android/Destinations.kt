package com.arkangel.ulessontechnicaltest.android

sealed class Destinations(
    val route: String
) {
    object Home : Destinations("/splash")
    object SubjectInfo : Destinations("/subject")
    object ChapterInfo : Destinations("/chapter")
    object LessonPlayer : Destinations("/lesson-player")
}

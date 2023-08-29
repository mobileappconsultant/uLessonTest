package com.arkangel.ulessontechnicaltest.features.chapter.api

import com.arkangel.ulessontechnicaltest.features.chapter.model.Chapter
import com.arkangel.ulessontechnicaltest.utils.Endpoints
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

class MockChapterApi(
    private val client: HttpClient
): ChapterApi {
    override suspend fun getChapters(subject: String): List<Chapter> {
        if (subject.lowercase() != "biology") {
            return listOf()
        }

        return client.get {
            url(urlString = Endpoints.GET_CHAPTERS)
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }.call.body()
    }
}
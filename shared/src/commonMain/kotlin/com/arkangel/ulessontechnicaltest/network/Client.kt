package com.arkangel.ulessontechnicaltest.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

@OptIn(ExperimentalSerializationApi::class)
internal fun makeClient() = HttpClient {
    expectSuccess = true

    install(WebSockets)

    install(Logging) {
        logger = KtorLogger(true)
        level = LogLevel.ALL
    }

    install(ResponseObserver) {
        onResponse { response ->
            println("Ktor response status code: ${response.status}")
        }
    }

    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
            prettyPrint = true
            isLenient = true
            explicitNulls = false
            encodeDefaults = true
            coerceInputValues = true
        })
    }
}
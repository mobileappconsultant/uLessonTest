package com.arkangel.ulessontechnicaltest.network

import io.ktor.client.plugins.logging.Logger

class KtorLogger(
    private val isDebug: Boolean,
): Logger {
    override fun log(message: String) {
        if (isDebug) {
            println("Logger Ktor => $message")
        }
    }
}
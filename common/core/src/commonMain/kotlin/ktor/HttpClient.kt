package ktor

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.config
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal fun createHttpClient(enableLoggingConfig: Boolean): HttpClient {
    return HttpClient(CIO) {
        if (enableLoggingConfig) {
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }
        }

        install(WebSockets)

        install(ContentNegotiation) {
            json(Json {
                allowSpecialFloatingPointValues = true
                isLenient = true
                ignoreUnknownKeys = true
                prettyPrint = true
            }, contentType = ContentType.Any)
        }

        defaultRequest {
            header("Content-Type", "application/json; charset=UTF-8")
        }
    }
}
package com.biologyapp_backend.plugins

import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation


/**
 * Инициализация библиотеки serialization.kotlinx.json
 * */
fun Application.setupSerialization() {
    install(ContentNegotiation) {
        json()
    }
}

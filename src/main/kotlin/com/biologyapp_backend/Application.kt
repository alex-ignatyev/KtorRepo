package com.biologyapp_backend

import com.biologyapp_backend.feature.configureTestRouting
import com.biologyapp_backend.feature.login.configureLoginRouting
import com.biologyapp_backend.feature.register.configureRegisterRouting
import com.biologyapp_backend.plugins.setupSerialization
import io.ktor.server.cio.CIO
import io.ktor.server.engine.embeddedServer

// "http://0.0.0.0:8080" - BaseUrl сервера

fun main() {
    embeddedServer(CIO, port = 8080, host = "0.0.0.0") {
        setupSerialization()

        configureTestRouting()
        configureLoginRouting()
        configureRegisterRouting()
    }.start(wait = true)
}

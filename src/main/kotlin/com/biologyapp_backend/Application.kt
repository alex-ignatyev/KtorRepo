package com.biologyapp_backend

import com.biologyapp_backend.feature.login.configureLoginRouting
import com.biologyapp_backend.feature.register.configureRegisterRouting
import com.biologyapp_backend.plugins.configureRouting
import com.biologyapp_backend.plugins.configureSerialization
import io.ktor.server.cio.CIO
import io.ktor.server.engine.embeddedServer

fun main() {
    embeddedServer(CIO, port = 8080, host = "0.0.0.0") {
        configureSerialization()
        configureRouting()
        configureLoginRouting()
        configureRegisterRouting()
    }.start(wait = true)
}

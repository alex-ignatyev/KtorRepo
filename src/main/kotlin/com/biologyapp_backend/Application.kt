package com.biologyapp_backend

import com.biologyapp_backend.plugins.configureRouting
import com.biologyapp_backend.plugins.configureSerialization
import io.ktor.server.cio.CIO
import io.ktor.server.engine.embeddedServer

fun main() {
    embeddedServer(CIO, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureSerialization()
    }.start(wait = true)
}

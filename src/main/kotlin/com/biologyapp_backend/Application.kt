package com.biologyapp_backend

import com.biologyapp_backend.db.DatabaseFactory
import com.biologyapp_backend.feature.configureTestRouting
import com.biologyapp_backend.feature.login.configureLoginRouting
import com.biologyapp_backend.feature.register.configureRegisterRouting
import com.biologyapp_backend.feature.restore.configureRestorePasswordRouting
import com.biologyapp_backend.plugins.setupSerialization
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = System.getenv("PORT").toInt()) {
        setupSerialization()
        DatabaseFactory.init()

        configureTestRouting()
        configureLoginRouting()
        configureRegisterRouting()
        configureRestorePasswordRouting()
    }.start(wait = true)
}
package com.biologyapp_backend

import com.biologyapp_backend.feature.configureTestRouting
import com.biologyapp_backend.feature.login.configureLoginRouting
import com.biologyapp_backend.feature.register.configureRegisterRouting
import com.biologyapp_backend.feature.restore.configureRestorePasswordRouting
import com.biologyapp_backend.plugins.setupSerialization
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.jetbrains.exposed.sql.Database

fun main() {
    //TODO Почитать как работают HikariConfig и что это
    val config = HikariConfig("hikari.properties")
    val dataSource = HikariDataSource(config)
    Database.connect(dataSource)

    embeddedServer(Netty, port = 5432) {
    //embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        setupSerialization()

        configureTestRouting()
        configureLoginRouting()
        configureRegisterRouting()
        configureRestorePasswordRouting()
    }.start(wait = true)
}

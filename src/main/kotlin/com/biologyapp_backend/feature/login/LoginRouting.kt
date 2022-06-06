package com.biologyapp_backend.feature.login

import com.biologyapp_backend.cache.MemoryCash
import com.biologyapp_backend.cache.TokenCache
import com.biologyapp_backend.feature.login.model.LoginRequest
import com.biologyapp_backend.feature.login.model.LoginResponse
import com.biologyapp_backend.utils.generateUUID
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import java.util.UUID

fun Application.configureLoginRouting() {

    routing {
        post("/login") {
            val receive = call.receive<LoginRequest>()
            if (MemoryCash.users.map { it.login }.contains(receive.login)) {
                val token = generateUUID()
                MemoryCash.tokens.add(TokenCache(receive.login, token))
                call.respond(HttpStatusCode.OK, LoginResponse(token))
                return@post
            }
            call.respond(HttpStatusCode.NotFound, "User Not Found")
        }
    }
}


package com.biologyapp_backend.feature.register

import com.biologyapp_backend.cache.MemoryCash
import com.biologyapp_backend.cache.TokenCache
import com.biologyapp_backend.feature.register.model.RegistrationRequest
import com.biologyapp_backend.feature.register.model.RegistrationResponse
import com.biologyapp_backend.utils.generateUUID
import com.biologyapp_backend.utils.isValidEmail
import com.biologyapp_backend.utils.isValidPassword
import com.biologyapp_backend.utils.isValidRepeatPassword
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import java.util.UUID

fun Application.configureRegisterRouting() {

    routing {
        post("/register") {
            val receive = call.receive<RegistrationRequest>()
            if (!receive.email.isValidEmail()) {
                call.respond(HttpStatusCode.BadRequest, "Email is not valid")
                return@post
            }

            if (MemoryCash.users.map { it.login }.contains(receive.login)) {
                call.respond(HttpStatusCode.Conflict, "User already exists")
                return@post
            }

            if (!receive.password.isValidPassword()){
                call.respond(HttpStatusCode.Conflict, "Password is not valid")
                return@post
            }

            if (!receive.repeatPassword.isValidRepeatPassword(receive.password)){
                call.respond(HttpStatusCode.Conflict, "The entered passwords do not match")
                return@post
            }

            val token = generateUUID()
            MemoryCash.users.add(receive)
            MemoryCash.tokens.add(TokenCache(receive.login, token))
            call.respond(HttpStatusCode.OK, RegistrationResponse(token))
        }
    }
}


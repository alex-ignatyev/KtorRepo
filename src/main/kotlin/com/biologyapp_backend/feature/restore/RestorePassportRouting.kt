package com.biologyapp_backend.feature.restore

import com.biologyapp_backend.cache.MemoryCash
import com.biologyapp_backend.feature.register.model.RegistrationResponse
import com.biologyapp_backend.feature.restore.model.RestorePassportRequest
import com.biologyapp_backend.utils.generateUUID
import com.biologyapp_backend.utils.isValidPassword
import com.biologyapp_backend.utils.isValidRepeatPassword
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.post
import io.ktor.server.routing.routing

fun Application.configureRestorePasswordRouting() {

    routing {
        post("/restore_password") {
            val receive = call.receive<RestorePassportRequest>()

            if (!receive.newPassword.isValidPassword()) {
                call.respond(HttpStatusCode.Conflict, "Password is not valid")
                return@post
            }

            if (!receive.repeatNewPassword.isValidRepeatPassword(receive.newPassword)) {
                call.respond(HttpStatusCode.Conflict, "The entered passwords do not match")
                return@post
            }

            val desiredUser = MemoryCash.users.find { it.login == receive.login && it.email == receive.email }
            if (desiredUser == null) {
                call.respond(HttpStatusCode.NotFound, "User Not Found")
                return@post
            }
            val userWithRestorePassword = desiredUser.copy(
                password = receive.newPassword,
                repeatPassword = receive.repeatNewPassword
            )
            MemoryCash.users.remove(desiredUser)
            MemoryCash.users.add(userWithRestorePassword)
            call.respond(HttpStatusCode.OK, RegistrationResponse(updateAndGetToken(desiredUser.login)))
        }
    }
}

fun updateAndGetToken(userLogin: String): String {
    var token = ""
    for (user in MemoryCash.tokens) {
        if (userLogin == user.login) {
            token = generateUUID()
            val userWithRestoreToken = user.copy(token = token)
            MemoryCash.tokens.remove(user)
            MemoryCash.tokens.add(userWithRestoreToken)
        }
    }
    return token
}
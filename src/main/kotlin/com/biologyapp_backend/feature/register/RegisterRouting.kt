package com.biologyapp_backend.feature.register

import com.biologyapp_backend.db.facade.DAOFacadeImpl
import com.biologyapp_backend.feature.register.model.RegistrationRequest
import com.biologyapp_backend.feature.register.model.RegistrationResponse
import com.biologyapp_backend.feature.register.model.toUser
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

fun Application.configureRegisterRouting() {

    val fascade = DAOFacadeImpl()

    routing {
        post("/register") {
            val receive = call.receive<RegistrationRequest>()

            if (fascade.getAllUsers().map { it.login }.contains(receive.login)) {
                call.respond(HttpStatusCode.Conflict, "User already exists")
                return@post
            }

            if (!receive.email.isValidEmail()) {
                call.respond(HttpStatusCode.BadRequest, "Email is not valid")
                return@post
            }

            if (fascade.getAllUsers().map { it.email }.contains(receive.email)) {
                call.respond(HttpStatusCode.BadRequest, "U can't use this email")
                return@post
            }

            if (!receive.password.isValidPassword()) {
                call.respond(HttpStatusCode.Conflict, "Password is not valid")
                return@post
            }

            if (!receive.repeatPassword.isValidRepeatPassword(receive.password)) {
                call.respond(HttpStatusCode.Conflict, "The entered passwords do not match")
                return@post
            }

            val token = generateUUID()
            fascade.addUser(receive.toUser(token))
            call.respond(HttpStatusCode.OK, RegistrationResponse(token))
        }
    }
}


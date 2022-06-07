package com.biologyapp_backend.feature

import com.biologyapp_backend.db.facade.DAOFacadeImpl
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

fun Application.configureTestRouting() {

    val fascade = DAOFacadeImpl()

    routing {
        get("/test") {
            call.respond(HttpStatusCode.OK, fascade.getAllUsers())
        }
    }
}

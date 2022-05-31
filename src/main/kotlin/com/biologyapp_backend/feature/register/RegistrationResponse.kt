package com.biologyapp_backend.feature.register

import kotlinx.serialization.Serializable

@Serializable
data class RegistrationResponse(
    val token: String,
    val password: String,
    val repeatedPassword: String
)

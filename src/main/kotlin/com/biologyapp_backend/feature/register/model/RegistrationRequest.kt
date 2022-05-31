package com.biologyapp_backend.feature.register.model

import kotlinx.serialization.Serializable

@Serializable
data class RegistrationRequest(
    val login: String,
    val email: String,
    val password: String,
    val repeatPassword: String
)

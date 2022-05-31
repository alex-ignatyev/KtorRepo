package com.biologyapp_backend.feature.register.model

import kotlinx.serialization.Serializable

@Serializable
data class RegistrationResponse(
    val token: String
)

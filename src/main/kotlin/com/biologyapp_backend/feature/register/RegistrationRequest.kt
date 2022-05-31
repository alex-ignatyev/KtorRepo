package com.biologyapp_backend.feature.register

import kotlinx.serialization.Serializable

@Serializable
data class RegistrationRequest(
    val login: String
)

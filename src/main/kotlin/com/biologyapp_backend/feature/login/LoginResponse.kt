package com.biologyapp_backend.feature.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val token: String
)

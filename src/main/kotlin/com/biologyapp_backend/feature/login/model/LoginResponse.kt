package com.biologyapp_backend.feature.login.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val token: String
)

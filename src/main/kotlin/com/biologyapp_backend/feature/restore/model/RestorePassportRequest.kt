package com.biologyapp_backend.feature.restore.model

import kotlinx.serialization.Serializable

@Serializable
data class RestorePassportRequest(
    val login: String,
    val email: String,
    val newPassword: String,
    val repeatNewPassword: String
)

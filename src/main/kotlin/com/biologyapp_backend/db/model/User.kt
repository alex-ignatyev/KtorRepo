package com.biologyapp_backend.db.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String= "5",
    val login: String,
    val email: String,
    val password: String,
    val token: String,
    val firstName: String,
    val secondName: String,
    val birthday: String,
    val phone: String,
    val clas: Int
)

package com.biologyapp_backend.feature.register.model

import com.biologyapp_backend.db.model.User
import kotlinx.serialization.Serializable

@Serializable
data class RegistrationRequest(
    val login: String,
    val email: String,
    val password: String,
    val repeatPassword: String,
    val firstName: String,
    val secondName: String,
    val birthday: String,
    val phone: String,
    val clas: Int,
)

fun RegistrationRequest.toUser(token: String) = User(
    login = this.login,
    email = this.email,
    password = this.password,
    token = token,
    firstName = this.firstName,
    secondName = this.secondName,
    birthday = this.birthday,
    phone = this.phone,
    clas = this.clas,
)

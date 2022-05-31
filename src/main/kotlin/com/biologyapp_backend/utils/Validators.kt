package com.biologyapp_backend.utils

fun String.isValidEmail(): Boolean {
    return this.contains("@")
}

fun String.isValidPassword(): Boolean {
    return this.length in 4..10
}

fun String.isValidRepeatPassword(password: String): Boolean {
    return this == password
}
package com.biologyapp_backend.utils

fun String.isValidEmail(): Boolean {
    return this.contains("@")
}
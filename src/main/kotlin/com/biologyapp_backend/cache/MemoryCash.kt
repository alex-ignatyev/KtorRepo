package com.biologyapp_backend.cache

import com.biologyapp_backend.feature.register.model.RegistrationRequest

/**
 * Тестовая замена БД
 * */
object MemoryCash {
    val users: MutableList<RegistrationRequest> = mutableListOf()
    val tokens: MutableList<TokenCache> = mutableListOf()
}

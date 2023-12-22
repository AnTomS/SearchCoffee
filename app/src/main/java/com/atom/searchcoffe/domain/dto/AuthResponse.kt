package com.atom.searchcoffe.domain.dto

data class AuthResponse(
    val token: String,
    val tokenLifetime: Long
)

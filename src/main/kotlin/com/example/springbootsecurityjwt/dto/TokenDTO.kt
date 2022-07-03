package com.example.springbootsecurityjwt.dto

data class TokenDTO(
    val tokenType: String = "Bearer",
    val accessToken: String,
    val expiresIn: Long
) {
}
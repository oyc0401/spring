package com.example.demo.auth

fun getTokenFromHeader(authHeader: String?): String {
    if (authHeader.isNullOrBlank() || !authHeader.startsWith("Bearer ")) {
        throw IllegalArgumentException("Invalid Authorization header")
    }
    return authHeader.removePrefix("Bearer ").trim()
}
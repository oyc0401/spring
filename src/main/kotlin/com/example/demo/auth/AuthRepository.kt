package com.example.demo.auth

import org.springframework.data.jpa.repository.JpaRepository

interface AuthRepository : JpaRepository<Auth, Int> {
    fun findByUsername(username: String): Auth?

    fun findByLoginProviderAndOauthId(provider: String, uid: String): Auth?;

    fun existsByUsername(username: String): Boolean;
}

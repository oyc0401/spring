package com.example.demo.auth

import org.springframework.data.jpa.repository.JpaRepository

interface AuthRepository : JpaRepository<Auth, Int> {
    fun findByEmail(email: String): Auth?
}

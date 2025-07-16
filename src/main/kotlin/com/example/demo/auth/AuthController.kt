package com.example.demo.auth

import com.example.demo.auth.LoginRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): Map<String, String> {
        val accessToken = authService.login(request)
        return mapOf("accessToken" to accessToken)
    }
}

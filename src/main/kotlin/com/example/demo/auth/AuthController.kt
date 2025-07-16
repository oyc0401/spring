package com.example.demo.auth

import com.example.demo.auth.LoginRequest
import org.springframework.web.bind.annotation.*
import com.example.demo.user.User
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity

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

    @GetMapping("/me")
    fun getMyInfo(request: HttpServletRequest): ResponseEntity<User> {
        val user = authService.getMyInfo(request)
        return ResponseEntity.ok(user)
    }
}

package com.example.demo.auth

import org.springframework.web.bind.annotation.*
import com.example.demo.user.User

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authService: AuthService
) {
    data class AccessTokenResponse(
        val accessToken: String
    )

    data class RefreshTokenRequest(
        val refreshToken: String
    )

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): AuthService.LoginResponse {
        return authService.login(request)
    }

    @PostMapping("/refresh")
    fun refresh(@RequestBody request: RefreshTokenRequest): AccessTokenResponse {
        val newAccessToken = authService.reissueAccessToken(request.refreshToken)
        return AccessTokenResponse(newAccessToken)
    }


    @PostMapping("/logout")
    fun logout(@RequestHeader("Authorization") authHeader: String) {
        authService.logout(authHeader)
    }

    @GetMapping("/me")
    fun getMyInfo(@RequestHeader("Authorization") authHeader: String): User {
        return authService.getMyInfo(authHeader)
    }

}

package com.example.demo.auth

import com.example.demo.user.User
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.web.bind.annotation.*

@SecurityRequirement(name = "bearerAuth")
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

    @PostMapping("/register")
    fun register(@RequestBody user: AuthService.SignupRequest) = authService.registerByEmail(user)


    @PostMapping("/login")
    fun login(@RequestBody request: AuthService.LoginRequest): AuthService.LoginResponse {
        return authService.login(request)
    }

    @PostMapping("/refresh")
    fun refresh(@RequestBody request: RefreshTokenRequest): AccessTokenResponse {
        val newAccessToken = authService.reissueAccessToken(request.refreshToken)
        return AccessTokenResponse(newAccessToken)
    }

    @PostMapping("/logout")
    fun logout(@RequestHeader("Authorization") authHeader: String?) {
        val token = getTokenFromHeader(authHeader)
        authService.logout(token)
    }

    @GetMapping("/me")
    fun getMyInfo(@RequestHeader("Authorization") authHeader: String?): User {
        val token = getTokenFromHeader(authHeader)

        return authService.getMyInfo(token)
    }

    @DeleteMapping("/me")
    fun remove(@RequestHeader("Authorization") authHeader: String?) {
        val token = getTokenFromHeader(authHeader);

        authService.remove(token)
    }


}

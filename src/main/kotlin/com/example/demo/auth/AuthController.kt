package com.example.demo.auth

import com.example.demo.auth.security.UserPrincipal
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.security.core.annotation.AuthenticationPrincipal
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
    fun register(@RequestBody user: AuthService.SignupRequest) = authService.registerByCredentials(user)


    @PostMapping("/register/google")
    fun registerGoogle(@RequestBody user: AuthService.SignupProviderRequest) {
        authService.registerByGoogle(user)
    }


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
    fun logout(@AuthenticationPrincipal user: UserPrincipal) {
        authService.logout(user.userId)
    }

    @DeleteMapping("/delete")
    fun remove(@AuthenticationPrincipal user: UserPrincipal) {
        authService.delete(user.userId)
    }


}

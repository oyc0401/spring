package com.example.demo.auth
import org.springframework.web.bind.annotation.*

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
    fun logout(@RequestHeader("Authorization") authHeader: String) {
        authService.logout(authHeader)
    }

    @DeleteMapping("/{id}")
    fun remove(@PathVariable id: Int) = authService.delete(id)



}

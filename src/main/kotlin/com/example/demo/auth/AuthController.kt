package com.example.demo.auth

import com.example.demo.security.UserPrincipal
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@SecurityRequirement(name = "bearerAuth")
@RestController
@Tag(name = "auth-controller", description = "인증/인가 API")
@RequestMapping("/api/auth")
class AuthController(
    private val authService: AuthService,
) {
    data class AccessTokenResponse(
        val accessToken: String
    )

    data class RefreshTokenRequest(
        val refreshToken: String
    )

    @Operation(summary = "일반 회원가입", description = "이메일과 비밀번호로 회원가입합니다")
    @PostMapping("/register")
    fun register(@RequestBody user: AuthService.SignupRequest) = authService.registerByCredentials(user)

    @Operation(summary = "구글 회원가입", description = "구글 계정으로 회원가입합니다")
    @PostMapping("/register/google")
    fun registerGoogle(@RequestBody user: AuthService.SignupProviderRequest) =
        authService.registerByGoogle(user)

    @Operation(summary = "일반 로그인", description = "이메일과 비밀번호로 로그인합니다")
    @PostMapping("/login")
    fun login(@RequestBody request: AuthService.LoginRequest): AuthService.LoginResponse {
        return authService.login(request)
    }

    @Operation(summary = "구글 로그인", description = "구글 계정으로 로그인합니다")
    @PostMapping("/loginGoogle")
    fun loginGoogle(@RequestBody request: AuthService.LoginProviderRequest): AuthService.LoginResponse {
        return authService.loginGoogle(request)
    }

    @Operation(summary = "토큰 갱신", description = "리프레시 토큰으로 액세스 토큰을 갱신합니다")
    @PostMapping("/refresh")
    fun refresh(@RequestBody request: RefreshTokenRequest): AccessTokenResponse {
        val newAccessToken = authService.reissueAccessToken(request.refreshToken)
        return AccessTokenResponse(newAccessToken)
    }

    @Operation(summary = "로그아웃", description = "현재 사용자를 로그아웃시킵니다")
    @PostMapping("/logout")
    fun logout(@AuthenticationPrincipal user: UserPrincipal) {
        authService.logout(user.userId)
    }




}

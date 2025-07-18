package com.example.demo.auth

import com.example.demo.auth.security.JwtTokenProvider
import com.example.demo.user.User
import com.example.demo.user.UserRepository
import org.springframework.stereotype.Service
import javax.naming.AuthenticationException

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository,
    private val jwtTokenProvider: JwtTokenProvider
) {

    data class SignupRequest(
        val email: String,
        val password: String,
        val name: String,
    )

    data class SignupProviderRequest(
        val idToken: String,
        val name: String,
    )

    data class LoginRequest(
        val email: String,
        val password: String
    )

    data class LoginResponse(
        val accessToken: String,
        val refreshToken: String
    )

    fun registerByEmail(request: SignupRequest): User {


        val user = userRepository.save(
            User(
                name = request.name
            )
        )

        val auth = Auth(
            userId = user.id,
            email = request.email,
            password = request.password,
            loginProvider = "email",
            role = "USER"
        )

        authRepository.save(auth)

        return user
    }

    fun registerByGoogle(request: SignupProviderRequest): User {
        val sub = getUidFromGoogleToken(request.idToken)


        val user = userRepository.save(
            User(
                name = request.name
            )
        )

        val auth = Auth(
            userId = user.id,
            oauthId = sub,
            loginProvider = "google",
            role = "USER",
        )

        authRepository.save(auth)
        return user
    }

    fun login(request: LoginRequest): LoginResponse {
        val auth = authRepository.findByEmail(request.email)
            ?: throw NoSuchElementException("User not found")

        if (auth.password != request.password) {
            throw AuthenticationException("Invalid password")
        }

        val accessToken = jwtTokenProvider.generateAccessToken(auth.userId, auth.role)
        val refreshToken = jwtTokenProvider.generateRefreshToken(auth.userId)

        return LoginResponse(accessToken, refreshToken)
    }

    fun reissueAccessToken(refreshToken: String): String {
        if (!jwtTokenProvider.validateRefreshToken(refreshToken)) {
            throw AuthenticationException("Invalid or expired refresh token")
        }

        val userId = jwtTokenProvider.getUserIdFromRefreshToken(refreshToken)

        val auth = authRepository.findById(userId)
            .orElseThrow { NoSuchElementException("User not found") }

        return jwtTokenProvider.generateAccessToken(userId, auth.role)
    }

    fun logout(userId: Int) {
        jwtTokenProvider.removeRefreshToken(userId)
    }

    fun delete(userId: Int) {
        return userRepository.deleteById(userId) // auth는 cascade로 delete됌.
    }


}

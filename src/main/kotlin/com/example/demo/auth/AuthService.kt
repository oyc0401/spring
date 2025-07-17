package com.example.demo.auth

import com.example.demo.user.User
import com.example.demo.user.UserRepository
import org.springframework.stereotype.Service
import javax.naming.AuthenticationException

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val jwtTokenProvider: JwtTokenProvider
) {

    data class SignupRequest(
        val email: String,
        val password: String,
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
        val user = User(
            email = request.email,
            password = request.password,
            name = request.name,
            loginProvider = "email",
        )
        return userRepository.save(user)
    }

    fun login(request: LoginRequest): LoginResponse {
        val user = userRepository.findByEmail(request.email)
            ?: throw NoSuchElementException("User not found")

        if (user.password != request.password) {
            throw AuthenticationException("Invalid password")
        }

        val accessToken = jwtTokenProvider.generateAccessToken(user.id)
        val refreshToken = jwtTokenProvider.generateRefreshToken(user.id)

        return LoginResponse(accessToken, refreshToken)
    }

    fun getMyInfo(token: String): User {
        if (!jwtTokenProvider.validateToken(token)) {
            throw AuthenticationException("Invalid or expired token")
        }

        val userId = jwtTokenProvider.getUserIdFromToken(token)

        return userRepository.findById(userId)
            .orElseThrow { NoSuchElementException("User not found") }
    }

    fun reissueAccessToken(refreshToken: String): String {
        if (!jwtTokenProvider.validateRefreshToken(refreshToken)) {
            throw AuthenticationException("Invalid or expired refresh token")
        }

        val userId = jwtTokenProvider.getUserIdFromRefreshToken(refreshToken)

        return jwtTokenProvider.generateAccessToken(userId)
    }

    fun logout(token: String) {
        if (!jwtTokenProvider.validateToken(token)) {
            return
        }

        val userId = jwtTokenProvider.getUserIdFromToken(token)
        jwtTokenProvider.removeRefreshToken(userId)
    }

    fun remove(token: String) {
        if (!jwtTokenProvider.validateToken(token)) {
            return
        }
        val userId = jwtTokenProvider.getUserIdFromToken(token);
        return userRepository.deleteById(userId)
    }


}

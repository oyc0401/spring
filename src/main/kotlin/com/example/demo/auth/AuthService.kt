package com.example.demo.auth

import com.example.demo.auth.LoginRequest
import com.example.demo.user.UserRepository
import org.springframework.stereotype.Service
import com.example.demo.user.User
import jakarta.servlet.http.HttpServletRequest
@Service
class AuthService(
    private val userRepository: UserRepository,
    private val jwtTokenProvider: JwtTokenProvider
) {

    fun login(request: LoginRequest): String {
        val user = userRepository.findByUsername(request.username)
            ?: throw IllegalArgumentException("User not found")

        if (user.password != request.password) {
            throw IllegalArgumentException("Invalid password")
        }

        // access token only (refresh token은 나중에 추가)
        return jwtTokenProvider.generateAccessToken(user.id)
    }

    fun getMyInfo(authHeader: String): User {
        if (!authHeader.startsWith("Bearer ")) {
            throw IllegalArgumentException("Invalid Authorization header")
        }

        val token = authHeader.removePrefix("Bearer ").trim()

        if (!jwtTokenProvider.validateToken(token)) {
            throw IllegalArgumentException("Invalid or expired token")
        }

        val userId = jwtTokenProvider.getUserIdFromToken(token)

        return userRepository.findById(userId)
            .orElseThrow { IllegalArgumentException("User not found") }
    }
}

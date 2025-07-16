package com.example.demo.auth

import com.example.demo.dto.LoginRequest
import com.example.demo.repository.UserRepository
import org.springframework.stereotype.Service

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
}

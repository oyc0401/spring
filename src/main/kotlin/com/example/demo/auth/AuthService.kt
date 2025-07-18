package com.example.demo.auth

import com.example.demo.auth.security.JwtTokenProvider
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
        val user = User(
            email = request.email,
            password = request.password,
            name = request.name,
            loginProvider = "email",
        )
        return userRepository.save(user)
    }

    fun registerByGoogle(request: SignupProviderRequest): User {
        val sub = getUidFromGoogleToken(request.idToken)

        val user = User(
            oauthId = sub,
            name = request.name,
            loginProvider = "google",
        )
        return userRepository.save(user)
    }

    fun login(request: LoginRequest): LoginResponse {
        val user = userRepository.findByEmail(request.email)
            ?: throw NoSuchElementException("User not found")

        if (user.password != request.password) {
            throw AuthenticationException("Invalid password")
        }
        val role = if (user.id == 5) "ROLE_ADMIN" else "ROLE_USER"

        val accessToken = jwtTokenProvider.generateAccessToken(user.id, role)
        val refreshToken = jwtTokenProvider.generateRefreshToken(user.id)

        return LoginResponse(accessToken, refreshToken)
    }

    fun getMyInfo(userId: Int): User {
        return userRepository.findById(userId)
            .orElseThrow { NoSuchElementException("User not found") }
    }

    fun reissueAccessToken(refreshToken: String): String {
        if (!jwtTokenProvider.validateRefreshToken(refreshToken)) {
            throw AuthenticationException("Invalid or expired refresh token")
        }

        val userId = jwtTokenProvider.getUserIdFromRefreshToken(refreshToken)

//        val user = userRepository.findById(userId)
//            .orElseThrow { NoSuchElementException("User not found") }

        val role = if (userId == 5) "ROLE_ADMIN" else "ROLE_USER"
        return jwtTokenProvider.generateAccessToken(userId, role)
    }

    fun logout(userId: Int) {
        jwtTokenProvider.removeRefreshToken(userId)
    }

    fun remove(userId: Int) {
        return userRepository.deleteById(userId)
    }


}

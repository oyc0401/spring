package com.example.demo.user

import com.example.demo.auth.JwtTokenProvider
import org.springframework.stereotype.Service
import javax.naming.AuthenticationException

@Service
class UserService(
    private val userRepository: UserRepository,
    private val jwtTokenProvider: JwtTokenProvider
) {

    fun findAll(): List<User> = userRepository.findAll()

    fun update(id: Int, dto: User): User {
        val entity = userRepository.findById(id).orElseThrow()
        val merged = entity.copy(email = dto.email, password = dto.password)
        return userRepository.save(merged)
    }

    fun getMyInfo(token: String): User {
        if (!jwtTokenProvider.validateToken(token)) {
            throw AuthenticationException("Invalid or expired token")
        }

        val userId = jwtTokenProvider.getUserIdFromToken(token)

        return userRepository.findById(userId)
            .orElseThrow { NoSuchElementException("User not found") }
    }


}
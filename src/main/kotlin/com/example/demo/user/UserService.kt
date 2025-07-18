package com.example.demo.user

import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun findAll(): List<User> = userRepository.findAll()

    fun update(id: Int, dto: User): User {
        val entity = userRepository.findById(id).orElseThrow { NoSuchElementException("User not found") }
        val merged = entity.copy(email = dto.email, password = dto.password)
        return userRepository.save(merged)
    }

    fun getMyInfo(userId: Int): User {
        return userRepository.findById(userId)
            .orElseThrow { NoSuchElementException("User not found") }
    }


}
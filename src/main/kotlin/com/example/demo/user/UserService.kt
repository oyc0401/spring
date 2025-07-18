package com.example.demo.user

import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun findAll(): List<User> = userRepository.findAll()

    fun getMyInfo(userId: Int): User {
        return userRepository.findById(userId)
            .orElseThrow { NoSuchElementException("User not found") }
    }


}
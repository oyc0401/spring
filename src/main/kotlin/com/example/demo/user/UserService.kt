package com.example.demo.user

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.NoSuchElementException

@Service
class UserService(
    private val userRepository: UserRepository,
    private val mapper: UserMapper
) {

    fun findAll(): List<User> = userRepository.findAll()

    fun getMyInfo(userId: Int): User {
        return userRepository.findById(userId)
            .orElseThrow { NoSuchElementException("User not found") }
    }

    @Transactional
    fun postStylePartialUpdate(id: Int, dto: UserUpdateDto): User {
        val user = userRepository.findById(id)
            .orElseThrow { NoSuchElementException("User $id not found") }

        mapper.partialUpdate(user, dto) // 있는 값만 반영
        return user // flush는 트랜잭션 종료 시
    }

    fun delete(userId: Int) {
        return userRepository.deleteById(userId) // auth는 cascade로 delete됌.
    }

    @Transactional
    fun softDelete(userId: Int) {
        val user = userRepository.findById(userId)
            .orElseThrow { NoSuchElementException("User $userId not found") }
        
        user.deleted = true
        user.deletedTime = LocalDateTime.now()
        
        userRepository.save(user)
    }
}
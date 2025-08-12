package com.example.demo.user

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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

}
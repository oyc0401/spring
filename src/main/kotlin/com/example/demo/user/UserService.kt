package com.example.demo.user

import org.springframework.stereotype.Service

@Service
class UserService(private val repo: UserRepository) {

    fun findAll(): List<User> = repo.findAll()

    fun create(user: User): User = repo.save(user)

    fun update(id: Int, dto: User): User {
        val entity = repo.findById(id).orElseThrow()
        val merged = entity.copy(username = dto.username, password = dto.password)
        return repo.save(merged)
    }

    fun delete(id: Int) = repo.deleteById(id)
}
package com.example.demo.service

import com.example.demo.entity.User
import com.example.demo.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val repo: UserRepository) {

    fun findAll(): List<User> = repo.findAll()

    fun create(user: User): User = repo.save(user)

    fun update(id: Int, dto: User): User {
        val entity = repo.findById(id).orElseThrow()
        val merged = entity.copy(name = dto.name, password = dto.password)
        return repo.save(merged)
    }

    fun delete(id: Int) = repo.deleteById(id)
}

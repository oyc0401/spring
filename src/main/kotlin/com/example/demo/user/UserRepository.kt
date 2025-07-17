package com.example.demo.user

import com.example.demo.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Int>{
    fun findByEmail(email: String): User?
}

package com.example.demo.user.contest

import org.springframework.data.jpa.repository.JpaRepository

interface ContestRepository : JpaRepository<Contest, Int> {

    fun findByUserId(userId: Int): List<Contest>
}

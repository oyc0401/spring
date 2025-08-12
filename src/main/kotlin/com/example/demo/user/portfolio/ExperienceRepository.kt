package com.example.demo.user.portfolio

import org.springframework.data.jpa.repository.JpaRepository

interface ExperienceRepository : JpaRepository<Experience, Int> {

    fun findByUserId(userId: Int): List<Experience>
}



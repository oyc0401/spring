package com.example.demo.user.experience

import org.springframework.data.jpa.repository.JpaRepository

interface ExperienceRepository : JpaRepository<Experience, Int> {

    fun findByUserId(userId: Int): List<Experience>
}



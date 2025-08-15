package com.example.demo.user.experience

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ExperienceRepository : JpaRepository<Experience, Int> {

    fun findByUserIdOrderByPriorityDescIdDesc(userId: Int): List<Experience>
    
    @Query("SELECT COALESCE(MAX(e.priority), 0) FROM Experience e WHERE e.userId = :userId")
    fun findMaxPriorityByUserId(userId: Int): Int
}



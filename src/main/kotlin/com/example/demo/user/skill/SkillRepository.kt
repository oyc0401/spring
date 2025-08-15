package com.example.demo.user.skill

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface SkillRepository : JpaRepository<Skill, Int> {

    fun findByUserIdOrderByPriorityDescIdDesc(userId: Int): List<Skill>
    
    @Query("SELECT COALESCE(MAX(s.priority), 0) FROM Skill s WHERE s.userId = :userId")
    fun findMaxPriorityByUserId(userId: Int): Int
}

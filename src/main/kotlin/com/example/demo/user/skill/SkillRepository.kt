package com.example.demo.user.skill

import org.springframework.data.jpa.repository.JpaRepository

interface SkillRepository : JpaRepository<Skill, Int> {

    fun findByUserId(userId: Int): List<Skill>
}

package com.example.demo.user.skill

import org.springframework.stereotype.Service
import java.util.NoSuchElementException

@Service
class SkillService(
    private val skillRepository: SkillRepository,
    private val mapper: SkillMapper
) {
    data class SkillRequest(
        val title: String,
        val details: String,
    )

    fun addSkill(userId: Int, request: SkillRequest): Skill {
        val skill = Skill(
            userId = userId,
            title = request.title,
            details = request.details
        )

        return skillRepository.save(skill)
    }

    fun getSkills(userId: Int): List<Skill> {
        return skillRepository.findByUserId(userId)
    }

    fun getSkill(userId: Int, skillId: Int): Skill {
        val skill = skillRepository.findById(skillId)
            .orElseThrow { NoSuchElementException("Skill not found") }

        if (skill.userId != userId) {
            throw IllegalArgumentException("Access denied: Skill does not belong to user")
        }

        return skill
    }

    fun updateSkill(userId: Int, skillId: Int, dto: SkillUpdateDto): Skill {
        val skill = skillRepository.findById(skillId)
            .orElseThrow { NoSuchElementException("Skill not found") }

        if (skill.userId != userId) {
            throw IllegalArgumentException("Access denied: Skill does not belong to user")
        }

        mapper.partialUpdate(skill, dto)
        return skill // JPA dirty checking으로 자동 저장
    }

    fun deleteSkill(userId: Int, skillId: Int) {
        val skill = skillRepository.findById(skillId)
            .orElseThrow { NoSuchElementException("Skill not found") }

        if (skill.userId != userId) {
            throw IllegalArgumentException("Access denied: Skill does not belong to user")
        }

        skillRepository.delete(skill)
    }
}

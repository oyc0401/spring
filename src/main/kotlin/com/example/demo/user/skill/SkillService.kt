package com.example.demo.user.skill

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
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

    data class SwapPriorityRequest(
        val skillId1: Int,
        val skillId2: Int
    )

    fun addSkill(userId: Int, request: SkillRequest): Skill {
        // 자동으로 사용자의 최대 priority + 1 할당
        val maxPriority = skillRepository.findMaxPriorityByUserId(userId)
        val skill = Skill(
            userId = userId,
            title = request.title,
            details = request.details,
            priority = maxPriority + 1
        )

        return skillRepository.save(skill)
    }

    fun getSkills(userId: Int): List<Skill> {
        return skillRepository.findByUserIdOrderByPriorityDescIdDesc(userId)
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

    @Transactional
    fun swapSkillPriority(userId: Int, skillId1: Int, skillId2: Int) {
        val skill1 = skillRepository.findById(skillId1)
            .orElseThrow { NoSuchElementException("Skill with id $skillId1 not found") }
        val skill2 = skillRepository.findById(skillId2)
            .orElseThrow { NoSuchElementException("Skill with id $skillId2 not found") }

        // 두 스킬 모두 해당 사용자 소유인지 확인
        if (skill1.userId != userId || skill2.userId != userId) {
            throw IllegalArgumentException("Access denied: Skills do not belong to user")
        }

        // 두 스킬의 priority 교체
        val tempPriority = skill1.priority
        skill1.priority = skill2.priority
        skill2.priority = tempPriority
        
        // JPA dirty checking으로 자동 저장
    }
}

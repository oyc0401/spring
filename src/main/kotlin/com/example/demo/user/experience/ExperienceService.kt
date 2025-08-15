package com.example.demo.user.experience

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.NoSuchElementException

@Service
class ExperienceService(
    private val experienceRepository: ExperienceRepository,
    private val mapper: ExperienceMapper
) {
    data class ExperienceRequest(
        val title: String,
        val details: String,
        val startDate: String? = null,
        val endDate: String? = null
    )

    data class SwapPriorityRequest(
        val experienceId1: Int,
        val experienceId2: Int
    )

    fun addExperience(userId: Int, request: ExperienceRequest): Experience {
        val maxPriority = experienceRepository.findMaxPriorityByUserId(userId)
        val experience = Experience(
            userId = userId,
            title = request.title,
            details = request.details,
            startDate = request.startDate,
            endDate = request.endDate,
            priority = maxPriority + 1
        )

        return experienceRepository.save(experience)
    }


    fun getExperiences(userId: Int): List<Experience> {
        return experienceRepository.findByUserIdOrderByPriorityDescIdDesc(userId)
    }

    fun getExperience(userId: Int, experienceId: Int): Experience {
        val experience = experienceRepository.findById(experienceId)
            .orElseThrow { NoSuchElementException("Experience not found") }

        if (experience.userId != userId) {
            throw IllegalArgumentException("Access denied: Experience does not belong to user")
        }

        return experience
    }

    fun updateExperience(userId: Int, experienceId: Int, dto: ExperienceUpdateDto): Experience {
        val experience = experienceRepository.findById(experienceId)
            .orElseThrow { NoSuchElementException("Experience not found") }

        if (experience.userId != userId) {
            throw IllegalArgumentException("Access denied: Experience does not belong to user")
        }

        mapper.partialUpdate(experience, dto)
        return experience // JPA dirty checking으로 자동 저장
    }

    fun deleteExperience(userId: Int, experienceId: Int) {
        val experience = experienceRepository.findById(experienceId)
            .orElseThrow { NoSuchElementException("Experience not found") }

        if (experience.userId != userId) {
            throw IllegalArgumentException("Access denied: Experience does not belong to user")
        }

        experienceRepository.delete(experience)
    }

    @Transactional
    fun swapExperiencePriority(userId: Int, experienceId1: Int, experienceId2: Int) {
        val experience1 = experienceRepository.findById(experienceId1)
            .orElseThrow { NoSuchElementException("Experience with id $experienceId1 not found") }
        val experience2 = experienceRepository.findById(experienceId2)
            .orElseThrow { NoSuchElementException("Experience with id $experienceId2 not found") }

        if (experience1.userId != userId || experience2.userId != userId) {
            throw IllegalArgumentException("Access denied: Experiences do not belong to user")
        }

        val tempPriority = experience1.priority
        experience1.priority = experience2.priority
        experience2.priority = tempPriority
    }
}
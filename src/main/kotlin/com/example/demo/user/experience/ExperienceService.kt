package com.example.demo.user.experience

import org.springframework.stereotype.Service
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

    fun addExperience(userId: Int, request: ExperienceRequest): Experience {
        val experience = Experience(
            userId = userId,
            title = request.title,
            details = request.details,
            startDate = request.startDate,
            endDate = request.endDate
        )

        return experienceRepository.save(experience)
    }


    fun getExperiences(userId: Int): List<Experience> {
        return experienceRepository.findByUserId(userId)
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
}
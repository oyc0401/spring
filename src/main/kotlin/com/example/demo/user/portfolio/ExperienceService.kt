package com.example.demo.user.portfolio

import com.example.demo.user.mapper.ExperienceMapper
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
    )

    data class ExperienceUpdateDto(
        val title: String?,
        val details: String?,
    )


    fun addExperience(userId: Int, request: ExperienceRequest): Experience {
        val experience = Experience(
            userId = userId,
            title = request.title,
            details = request.details
        )

        return experienceRepository.save(experience)
    }


    fun getExperiences(userId: Int): List<Experience> {
        return experienceRepository.findByUserId(userId)
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
}
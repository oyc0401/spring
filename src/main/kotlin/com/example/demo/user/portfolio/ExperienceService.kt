package com.example.demo.user.portfolio

import org.springframework.stereotype.Service


@Service
class ExperienceService(
    private val experienceRepository: ExperienceRepository,
) {
    data class ExperienceRequest(
        val title: String,
        val details: String,
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
}
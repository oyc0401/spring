package com.example.demo.user.portfolio

import com.example.demo.security.UserPrincipal
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/users/experience")
class ExperienceController(
    private val experienceService: ExperienceService
) {

    @GetMapping("/")
    fun getExperiences(
        @AuthenticationPrincipal user: UserPrincipal
    ): List<Experience> {
        return experienceService.getExperiences(user.userId)
    }

    @PostMapping("/add")
    fun addExperience(
        @AuthenticationPrincipal user: UserPrincipal,
        @RequestBody request: ExperienceService.ExperienceRequest
    ): Experience {
        return experienceService.addExperience(user.userId, request)
    }

    @PostMapping("/{id}/update")
    fun updateExperience(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable id: Int,
        @RequestBody dto: ExperienceUpdateDto
    ): Experience {
        return experienceService.updateExperience(user.userId, id, dto)
    }
}
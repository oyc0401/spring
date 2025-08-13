package com.example.demo.user.experience

import com.example.demo.security.UserPrincipal
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@SecurityRequirement(name = "bearerAuth")
@RestController
@Tag(name = "user.experience-controller", description = "보유 경력 API")
@RequestMapping("/user/experience")
class ExperienceController(
    private val experienceService: ExperienceService
) {

    @GetMapping("/")
    fun getExperiences(
        @AuthenticationPrincipal user: UserPrincipal
    ): List<Experience> {
        return experienceService.getExperiences(user.userId)
    }

    @GetMapping("/{id}")
    fun getExperience(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable id: Int
    ): Experience {
        return experienceService.getExperience(user.userId, id)
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

    @DeleteMapping("/{id}")
    fun deleteExperience(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable id: Int
    ): ResponseEntity<Unit> {
        experienceService.deleteExperience(user.userId, id)
        return ResponseEntity.ok().build()
    }
}
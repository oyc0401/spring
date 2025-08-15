package com.example.demo.user.experience

import com.example.demo.security.UserPrincipal
import io.swagger.v3.oas.annotations.Operation
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

    @Operation(summary = "내 경력 목록 조회", description = "현재 사용자의 보유 경력 목록을 조회합니다")
    @GetMapping("/")
    fun getExperiences(
        @AuthenticationPrincipal user: UserPrincipal
    ): List<Experience> {
        return experienceService.getExperiences(user.userId)
    }

    @Operation(summary = "경력 상세 조회", description = "특정 경력의 상세 정보를 조회합니다")
    @GetMapping("/{id}")
    fun getExperience(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable id: Int
    ): Experience {
        return experienceService.getExperience(user.userId, id)
    }

    @Operation(summary = "경력 추가", description = "새로운 보유 경력을 추가합니다")
    @PostMapping("/add")
    fun addExperience(
        @AuthenticationPrincipal user: UserPrincipal,
        @RequestBody request: ExperienceService.ExperienceRequest
    ): Experience {
        return experienceService.addExperience(user.userId, request)
    }

    @Operation(summary = "경력 수정", description = "기존 보유 경력 정보를 수정합니다")
    @PostMapping("/{id}/update")
    fun updateExperience(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable id: Int,
        @RequestBody dto: ExperienceUpdateDto
    ): Experience {
        return experienceService.updateExperience(user.userId, id, dto)
    }

    @Operation(summary = "경력 삭제", description = "보유 경력을 삭제합니다")
    @DeleteMapping("/{id}")
    fun deleteExperience(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable id: Int
    ): ResponseEntity<Unit> {
        experienceService.deleteExperience(user.userId, id)
        return ResponseEntity.ok().build()
    }
}
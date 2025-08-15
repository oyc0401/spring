package com.example.demo.user.skill

import com.example.demo.security.UserPrincipal
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@SecurityRequirement(name = "bearerAuth")
@RestController
@Tag(name = "user.skill-controller", description = "보유 기술 API")
@RequestMapping("/user/skill")
class SkillController(
    private val skillService: SkillService
) {

    @Operation(summary = "내 기술 목록 조회", description = "현재 사용자의 보유 기술 목록을 조회합니다")
    @GetMapping("/")
    fun getSkills(
        @AuthenticationPrincipal user: UserPrincipal
    ): List<Skill> {
        return skillService.getSkills(user.userId)
    }

    @Operation(summary = "기술 상세 조회", description = "특정 기술의 상세 정보를 조회합니다")
    @GetMapping("/{id}")
    fun getSkill(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable id: Int
    ): Skill {
        return skillService.getSkill(user.userId, id)
    }

    @Operation(summary = "기술 추가", description = "새로운 보유 기술을 추가합니다")
    @PostMapping("/add")
    fun addSkill(
        @AuthenticationPrincipal user: UserPrincipal,
        @RequestBody request: SkillService.SkillRequest
    ): Skill {
        return skillService.addSkill(user.userId, request)
    }

    @Operation(summary = "기술 수정", description = "기존 보유 기술 정보를 수정합니다")
    @PostMapping("/{id}/update")
    fun updateSkill(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable id: Int,
        @RequestBody dto: SkillUpdateDto
    ): Skill {
        return skillService.updateSkill(user.userId, id, dto)
    }

    @Operation(summary = "기술 삭제", description = "보유 기술을 삭제합니다")
    @DeleteMapping("/{id}")
    fun deleteSkill(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable id: Int
    ): ResponseEntity<Unit> {
        skillService.deleteSkill(user.userId, id)
        return ResponseEntity.ok().build()
    }
}

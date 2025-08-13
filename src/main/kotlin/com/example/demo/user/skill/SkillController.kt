package com.example.demo.user.skill

import com.example.demo.security.UserPrincipal
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

    @GetMapping("/")
    fun getSkills(
        @AuthenticationPrincipal user: UserPrincipal
    ): List<Skill> {
        return skillService.getSkills(user.userId)
    }

    @GetMapping("/{id}")
    fun getSkill(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable id: Int
    ): Skill {
        return skillService.getSkill(user.userId, id)
    }

    @PostMapping("/add")
    fun addSkill(
        @AuthenticationPrincipal user: UserPrincipal,
        @RequestBody request: SkillService.SkillRequest
    ): Skill {
        return skillService.addSkill(user.userId, request)
    }

    @PostMapping("/{id}/update")
    fun updateSkill(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable id: Int,
        @RequestBody dto: SkillUpdateDto
    ): Skill {
        return skillService.updateSkill(user.userId, id, dto)
    }

    @DeleteMapping("/{id}")
    fun deleteSkill(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable id: Int
    ): ResponseEntity<Unit> {
        skillService.deleteSkill(user.userId, id)
        return ResponseEntity.ok().build()
    }
}

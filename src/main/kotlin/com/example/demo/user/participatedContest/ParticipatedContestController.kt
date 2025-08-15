package com.example.demo.user.participatedContest

import com.example.demo.security.UserPrincipal
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@SecurityRequirement(name = "bearerAuth")
@RestController
@Tag(name = "user.participated-contents-controller", description = "참여 공모전 API")
@RequestMapping("/users/participated-contests")
class ParticipatedContestController(
    private val participatedContestService: ParticipatedContestService
) {

    @Operation(summary = "참여 공모전 목록 조회", description = "현재 사용자가 참여한 공모전 목록을 조회합니다")
    @GetMapping("/")
    fun getParticipatedContests(
        @AuthenticationPrincipal user: UserPrincipal
    ): List<ParticipatedContest> {
        return participatedContestService.getParticipatedContests(user.userId)
    }

    @Operation(summary = "참여 공모전 상세 조회", description = "특정 참여 공모전의 상세 정보를 조회합니다")
    @GetMapping("/{id}")
    fun getParticipatedContest(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable id: Int
    ): ParticipatedContest {
        return participatedContestService.getParticipatedContest(user.userId, id)
    }

    @Operation(summary = "참여 공모전 추가", description = "새로운 공모전 참여 기록을 추가합니다")
    @PostMapping("/add")
    fun addParticipatedContest(
        @AuthenticationPrincipal user: UserPrincipal,
        @RequestBody request: ParticipatedContestService.ParticipatedContestRequest
    ): ParticipatedContest {
        return participatedContestService.addParticipatedContest(user.userId, request)
    }

    @Operation(summary = "참여 공모전 수정", description = "기존 참여 공모전 정보를 수정합니다")
    @PostMapping("/{id}/update")
    fun updateParticipatedContest(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable id: Int,
        @RequestBody dto: ParticipatedContestUpdateDto
    ): ParticipatedContest {
        return participatedContestService.updateParticipatedContest(user.userId, id, dto)
    }

    @Operation(summary = "참여 공모전 삭제", description = "참여 공모전 기록을 삭제합니다")
    @DeleteMapping("/{id}")
    fun deleteParticipatedContest(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable id: Int
    ): ResponseEntity<Unit> {
        participatedContestService.deleteParticipatedContest(user.userId, id)
        return ResponseEntity.ok().build()
    }
}

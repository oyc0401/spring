package com.example.demo.user.participatedContest

import com.example.demo.security.UserPrincipal
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/users/participated-contests")
class ParticipatedContestController(
    private val participatedContestService: ParticipatedContestService
) {

    @GetMapping("/")
    fun getParticipatedContests(
        @AuthenticationPrincipal user: UserPrincipal
    ): List<ParticipatedContest> {
        return participatedContestService.getParticipatedContests(user.userId)
    }

    @GetMapping("/{id}")
    fun getParticipatedContest(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable id: Int
    ): ParticipatedContest {
        return participatedContestService.getParticipatedContest(user.userId, id)
    }

    @PostMapping("/add")
    fun addParticipatedContest(
        @AuthenticationPrincipal user: UserPrincipal,
        @RequestBody request: ParticipatedContestService.ParticipatedContestRequest
    ): ParticipatedContest {
        return participatedContestService.addParticipatedContest(user.userId, request)
    }

    @PostMapping("/{id}/update")
    fun updateParticipatedContest(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable id: Int,
        @RequestBody dto: ParticipatedContestUpdateDto
    ): ParticipatedContest {
        return participatedContestService.updateParticipatedContest(user.userId, id, dto)
    }

    @DeleteMapping("/{id}")
    fun deleteParticipatedContest(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable id: Int
    ): ResponseEntity<Unit> {
        participatedContestService.deleteParticipatedContest(user.userId, id)
        return ResponseEntity.ok().build()
    }
}

package com.example.demo.user.contest

import com.example.demo.security.UserPrincipal
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@SecurityRequirement(name = "bearerAuth")
@RestController
@Tag(name = "user.contest-controller", description = "참여 공모전 API")
@RequestMapping("/user/contest")
class ContestController(
    private val contestService: ContestService
) {

    @GetMapping("/")
    fun getContests(
        @AuthenticationPrincipal user: UserPrincipal
    ): List<Contest> {
        return contestService.getContests(user.userId)
    }

    @GetMapping("/{id}")
    fun getContest(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable id: Int
    ): Contest {
        return contestService.getContest(user.userId, id)
    }

    @PostMapping("/add")
    fun addContest(
        @AuthenticationPrincipal user: UserPrincipal,
        @RequestBody request: ContestService.ContestRequest
    ): Contest {
        return contestService.addContest(user.userId, request)
    }

    @PostMapping("/{id}/update")
    fun updateContest(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable id: Int,
        @RequestBody dto: ContestUpdateDto
    ): Contest {
        return contestService.updateContest(user.userId, id, dto)
    }

    @DeleteMapping("/{id}")
    fun deleteContest(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable id: Int
    ): ResponseEntity<Unit> {
        contestService.deleteContest(user.userId, id)
        return ResponseEntity.ok().build()
    }
}

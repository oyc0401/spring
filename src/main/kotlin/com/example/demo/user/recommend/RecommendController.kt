package com.example.demo.user.recommend

import com.example.demo.security.UserPrincipal
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/users/recommends")
class RecommendController(
    private val recommendService: RecommendService
) {

    @GetMapping("/")
    fun getRecommends(
        @AuthenticationPrincipal user: UserPrincipal
    ): List<Recommend> {
        return recommendService.getRecommends(user.userId)
    }

    @GetMapping("/{contentId}")
    fun getRecommend(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable contentId: Int
    ): Recommend {
        return recommendService.getRecommend(user.userId, contentId)
    }

    @PostMapping("/add")
    fun addRecommend(
        @AuthenticationPrincipal user: UserPrincipal,
        @RequestBody request: RecommendService.RecommendRequest
    ): Recommend {
        return recommendService.addRecommend(user.userId, request)
    }

    @PostMapping("/{contentId}/update")
    fun updateRecommend(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable contentId: Int,
        @RequestBody dto: RecommendUpdateDto
    ): Recommend {
        return recommendService.updateRecommend(user.userId, contentId, dto)
    }

    @DeleteMapping("/{contentId}")
    fun deleteRecommend(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable contentId: Int
    ): ResponseEntity<Unit> {
        recommendService.deleteRecommend(user.userId, contentId)
        return ResponseEntity.ok().build()
    }
}

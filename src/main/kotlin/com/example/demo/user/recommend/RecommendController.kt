package com.example.demo.user.recommend

import com.example.demo.security.UserPrincipal
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/users/recommend")
class RecommendController(
    private val recommendService: RecommendService
) {

    @GetMapping("/")
    fun getRecommends(
        @AuthenticationPrincipal user: UserPrincipal
    ): List<Recommend> {
        return recommendService.getRecommends(user.userId)
    }

    @GetMapping("/{id}")
    fun getRecommend(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable id: Int
    ): Recommend {
        return recommendService.getRecommend(user.userId, id)
    }

    @PostMapping("/add")
    fun addRecommend(
        @AuthenticationPrincipal user: UserPrincipal,
        @RequestBody request: RecommendService.RecommendRequest
    ): Recommend {
        return recommendService.addRecommend(user.userId, request)
    }

    @PostMapping("/{id}/update")
    fun updateRecommend(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable id: Int,
        @RequestBody dto: RecommendUpdateDto
    ): Recommend {
        return recommendService.updateRecommend(user.userId, id, dto)
    }

    @DeleteMapping("/{id}")
    fun deleteRecommend(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable id: Int
    ): ResponseEntity<Unit> {
        recommendService.deleteRecommend(user.userId, id)
        return ResponseEntity.ok().build()
    }
}

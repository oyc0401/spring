package com.example.demo.user.recommend

import com.example.demo.security.UserPrincipal
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@SecurityRequirement(name = "bearerAuth")
@RestController
@Tag(name = "user.recommends-controller", description = "추천받은 콘텐츠 API")
@RequestMapping("/users/recommends")
class RecommendController(
    private val recommendService: RecommendService
) {

    @Operation(summary = "추천받은 콘텐츠 목록 조회", description = "현재 사용자가 추천받은 콘텐츠 목록을 조회합니다")
    @GetMapping("/")
    fun getRecommends(
        @AuthenticationPrincipal user: UserPrincipal
    ): List<Recommend> {
        return recommendService.getRecommends(user.userId)
    }

    @Operation(summary = "추천 콘텐츠 상세 조회", description = "특정 추천 콘텐츠의 상세 정보를 조회합니다")
    @GetMapping("/{contentId}")
    fun getRecommend(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable contentId: Int
    ): Recommend {
        return recommendService.getRecommend(user.userId, contentId)
    }

    @Operation(summary = "콘텐츠 추천 추가", description = "새로운 콘텐츠 추천을 추가합니다")
    @PostMapping("/add")
    fun addRecommend(
        @AuthenticationPrincipal user: UserPrincipal,
        @RequestBody request: RecommendService.RecommendRequest
    ): Recommend {
        return recommendService.addRecommend(user.userId, request)
    }

    @Operation(summary = "추천 콘텐츠 수정", description = "기존 추천 콘텐츠 정보를 수정합니다")
    @PostMapping("/{contentId}/update")
    fun updateRecommend(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable contentId: Int,
        @RequestBody dto: RecommendUpdateDto
    ): Recommend {
        return recommendService.updateRecommend(user.userId, contentId, dto)
    }

    @Operation(summary = "추천 콘텐츠 삭제", description = "추천 목록에서 콘텐츠를 삭제합니다")
    @DeleteMapping("/{contentId}")
    fun deleteRecommend(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable contentId: Int
    ): ResponseEntity<Unit> {
        recommendService.deleteRecommend(user.userId, contentId)
        return ResponseEntity.ok().build()
    }
}

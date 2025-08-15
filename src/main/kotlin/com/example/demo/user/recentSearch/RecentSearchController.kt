package com.example.demo.user.recentSearch

import com.example.demo.security.UserPrincipal
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@SecurityRequirement(name = "bearerAuth")
@RestController
@Tag(name = "user.recent-search-controller", description = "최근 검색어 API")
@RequestMapping("/users/recent-search")
class RecentSearchController(
    private val recentSearchService: RecentSearchService
) {

    @Operation(summary = "최근 검색어 목록 조회", description = "현재 사용자의 최근 검색어 목록을 조회합니다")
    @GetMapping("/")
    fun getRecentSearches(
        @AuthenticationPrincipal user: UserPrincipal
    ): List<RecentSearch> {
        return recentSearchService.getRecentSearches(user.userId)
    }

    @Operation(summary = "최근 검색어 상세 조회", description = "특정 최근 검색어의 상세 정보를 조회합니다")
    @GetMapping("/{id}")
    fun getRecentSearch(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable id: Int
    ): RecentSearch {
        return recentSearchService.getRecentSearch(user.userId, id)
    }

    @Operation(summary = "최근 검색어 추가", description = "새로운 검색어를 최근 검색어 목록에 추가합니다")
    @PostMapping("/add")
    fun addRecentSearch(
        @AuthenticationPrincipal user: UserPrincipal,
        @RequestBody request: RecentSearchService.RecentSearchRequest
    ): RecentSearch {
        return recentSearchService.addRecentSearch(user.userId, request)
    }

    @Operation(summary = "최근 검색어 수정", description = "기존 최근 검색어 정보를 수정합니다")
    @PostMapping("/{id}/update")
    fun updateRecentSearch(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable id: Int,
        @RequestBody dto: RecentSearchUpdateDto
    ): RecentSearch {
        return recentSearchService.updateRecentSearch(user.userId, id, dto)
    }

    @Operation(summary = "최근 검색어 삭제", description = "최근 검색어를 목록에서 삭제합니다")
    @DeleteMapping("/{id}")
    fun deleteRecentSearch(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable id: Int
    ): ResponseEntity<Unit> {
        recentSearchService.deleteRecentSearch(user.userId, id)
        return ResponseEntity.ok().build()
    }
}

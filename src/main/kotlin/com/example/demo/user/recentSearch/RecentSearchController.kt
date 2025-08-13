package com.example.demo.user.recentSearch

import com.example.demo.security.UserPrincipal
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/users/recent-search")
class RecentSearchController(
    private val recentSearchService: RecentSearchService
) {

    @GetMapping("/")
    fun getRecentSearches(
        @AuthenticationPrincipal user: UserPrincipal
    ): List<RecentSearch> {
        return recentSearchService.getRecentSearches(user.userId)
    }

    @GetMapping("/{id}")
    fun getRecentSearch(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable id: Int
    ): RecentSearch {
        return recentSearchService.getRecentSearch(user.userId, id)
    }

    @PostMapping("/add")
    fun addRecentSearch(
        @AuthenticationPrincipal user: UserPrincipal,
        @RequestBody request: RecentSearchService.RecentSearchRequest
    ): RecentSearch {
        return recentSearchService.addRecentSearch(user.userId, request)
    }

    @PostMapping("/{id}/update")
    fun updateRecentSearch(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable id: Int,
        @RequestBody dto: RecentSearchUpdateDto
    ): RecentSearch {
        return recentSearchService.updateRecentSearch(user.userId, id, dto)
    }

    @DeleteMapping("/{id}")
    fun deleteRecentSearch(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable id: Int
    ): ResponseEntity<Unit> {
        recentSearchService.deleteRecentSearch(user.userId, id)
        return ResponseEntity.ok().build()
    }
}

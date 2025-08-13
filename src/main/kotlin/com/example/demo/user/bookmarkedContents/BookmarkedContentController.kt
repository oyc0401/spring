package com.example.demo.user.bookmarkedContents

import com.example.demo.security.UserPrincipal
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/users/bookmarked-contents")
class BookmarkedContentController(
    private val bookmarkedContentService: BookmarkedContentService
) {

    @GetMapping("/")
    fun getBookmarkedContents(
        @AuthenticationPrincipal user: UserPrincipal
    ): List<BookmarkedContent> {
        return bookmarkedContentService.getBookmarkedContents(user.userId)
    }

    @GetMapping("/{contentId}")
    fun getBookmarkedContent(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable contentId: Int
    ): BookmarkedContent {
        return bookmarkedContentService.getBookmarkedContent(user.userId, contentId)
    }

    @PostMapping("/add")
    fun addBookmarkedContent(
        @AuthenticationPrincipal user: UserPrincipal,
        @RequestBody request: BookmarkedContentService.BookmarkedContentRequest
    ): BookmarkedContent {
        return bookmarkedContentService.addBookmarkedContent(user.userId, request)
    }

    @PostMapping("/{contentId}/update")
    fun updateBookmarkedContent(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable contentId: Int,
        @RequestBody dto: BookmarkedContentUpdateDto
    ): BookmarkedContent {
        return bookmarkedContentService.updateBookmarkedContent(user.userId, contentId, dto)
    }

    @DeleteMapping("/{contentId}")
    fun deleteBookmarkedContent(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable contentId: Int
    ): ResponseEntity<Unit> {
        bookmarkedContentService.deleteBookmarkedContent(user.userId, contentId)
        return ResponseEntity.ok().build()
    }
}

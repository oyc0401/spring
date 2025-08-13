package com.example.demo.user.bookmarkedContents

import com.example.demo.security.UserPrincipal
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@SecurityRequirement(name = "bearerAuth")
@RestController
@Tag(name = "user.bookmarked-contents-controller", description = "스크랩한 콘텐츠 API")
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

    @PostMapping("/add")
    fun addBookmarkedContent(
        @AuthenticationPrincipal user: UserPrincipal,
        @RequestBody request: BookmarkedContentService.BookmarkedContentRequest
    ): BookmarkedContent {
        return bookmarkedContentService.addBookmarkedContent(user.userId, request)
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

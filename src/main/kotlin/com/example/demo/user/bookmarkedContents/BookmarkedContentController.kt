package com.example.demo.user.bookmarkedContents

import com.example.demo.security.UserPrincipal
import io.swagger.v3.oas.annotations.Operation
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

    @Operation(summary = "스크랩한 콘텐츠 목록 조회", description = "현재 사용자가 스크랩한 콘텐츠 목록을 조회합니다")
    @GetMapping("/")
    fun getBookmarkedContents(
        @AuthenticationPrincipal user: UserPrincipal
    ): List<BookmarkedContent> {
        return bookmarkedContentService.getBookmarkedContents(user.userId)
    }

    @Operation(summary = "콘텐츠 스크랩 추가", description = "새로운 콘텐츠를 스크랩 목록에 추가합니다")
    @PostMapping("/add")
    fun addBookmarkedContent(
        @AuthenticationPrincipal user: UserPrincipal,
        @RequestBody request: BookmarkedContentService.BookmarkedContentRequest
    ): BookmarkedContent {
        return bookmarkedContentService.addBookmarkedContent(user.userId, request)
    }

    @Operation(summary = "스크랩 콘텐츠 삭제", description = "스크랩 목록에서 콘텐츠를 삭제합니다")
    @DeleteMapping("/{contentId}")
    fun deleteBookmarkedContent(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable contentId: Int
    ): ResponseEntity<Unit> {
        bookmarkedContentService.deleteBookmarkedContent(user.userId, contentId)
        return ResponseEntity.ok().build()
    }
}

package com.example.demo.content

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@Tag(name = "content-controller", description = "콘텐츠 API")
@RequestMapping("/content")
class ContentController(
    private val contentService: ContentService
) {

    @Operation(summary = "전체 콘텐츠 조회", description = "모든 콘텐츠 목록을 조회합니다")
    @GetMapping("/")
    fun getContents(): List<Content> {
        return contentService.getContents()
    }

    @Operation(summary = "추천 콘텐츠 조회", description = "메인 페이지에 표시될 추천 콘텐츠를 조회합니다")
    @GetMapping("/featured")
    fun getFeaturedContents(): List<Content> {
        return contentService.getFeaturedContents()
    }

    @Operation(summary = "타입별 콘텐츠 조회", description = "특정 타입의 콘텐츠 목록을 조회합니다")
    @GetMapping("/type/{type}")
    fun getContentsByType(@PathVariable type: String): List<Content> {
        return contentService.getContentsByType(type)
    }

    @Operation(summary = "콘텐츠 상세 조회", description = "특정 콘텐츠의 상세 정보를 조회합니다")
    @GetMapping("/{id}")
    fun getContent(@PathVariable id: Int): Content {
        return contentService.getContent(id)
    }

    @Operation(summary = "콘텐츠 생성", description = "새로운 콘텐츠를 생성합니다")
    @PostMapping("/add")
    fun addContent(@RequestBody request: ContentService.ContentRequest): Content {
        return contentService.addContent(request)
    }

    @Operation(summary = "콘텐츠 수정", description = "기존 콘텐츠 정보를 수정합니다")
    @PostMapping("/{id}/update")
    fun updateContent(
        @PathVariable id: Int,
        @RequestBody dto: ContentUpdateDto
    ): Content {
        return contentService.updateContent(id, dto)
    }

    @Operation(summary = "콘텐츠 삭제", description = "특정 콘텐츠를 삭제합니다")
    @DeleteMapping("/{id}")
    fun deleteContent(@PathVariable id: Int): ResponseEntity<Unit> {
        contentService.deleteContent(id)
        return ResponseEntity.ok().build()
    }
}

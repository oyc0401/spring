package com.example.demo.content.tagType

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@SecurityRequirement(name = "bearerAuth")
@RestController
@Tag(name = "content.tag.tag-controller", description = "태그 종류 API")
@RequestMapping("/tags")
class TagController(
    private val tagService: TagService
) {

    @Operation(summary = "전체 태그 종류 조회", description = "시스템에서 사용 가능한 모든 태그 종류를 조회합니다")
    @GetMapping("/")
    fun getTags(): List<TagType> {
        return tagService.getTags()
    }

    @Operation(summary = "태그 상세 조회", description = "특정 태그의 상세 정보를 조회합니다")
    @GetMapping("/{tagId}")
    fun getTag(
        @PathVariable tagId: Int
    ): TagType {
        return tagService.getTag(tagId)
    }

    @Operation(summary = "새 태그 생성", description = "새로운 태그 종류를 생성합니다")
    @PostMapping("/add")
    fun addTag(
        @RequestBody request: TagService.TagRequest
    ): TagType {
        return tagService.addTag(request)
    }

    @Operation(summary = "태그 수정", description = "기존 태그 정보를 수정합니다")
    @PostMapping("/{tagId}/update")
    fun updateTag(
        @PathVariable tagId: Int,
        @RequestBody dto: TagUpdateDto
    ): TagType {
        return tagService.updateTag(tagId, dto)
    }

    @Operation(summary = "태그 삭제", description = "태그를 시스템에서 삭제합니다")
    @DeleteMapping("/{tagId}")
    fun deleteTag(
        @PathVariable tagId: Int
    ): ResponseEntity<Unit> {
        tagService.deleteTag(tagId)
        return ResponseEntity.ok().build()
    }
}

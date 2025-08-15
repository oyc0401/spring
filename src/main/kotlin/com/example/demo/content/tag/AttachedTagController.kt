package com.example.demo.content.tag

import com.example.demo.security.UserPrincipal
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@SecurityRequirement(name = "bearerAuth")
@RestController
@Tag(name = "content.tag.attached-tags-controller", description = "콘텐츠 태그 API")
@RequestMapping("/contents/{contentId}/tags")
class AttachedTagController(
    private val attachedTagService: AttachedTagService
) {

    @Operation(summary = "콘텐츠 태그 목록 조회", description = "특정 콘텐츠에 연결된 태그 목록을 조회합니다")
    @GetMapping("/")
    fun getAttachedTags(
        @PathVariable contentId: Int
    ): List<AttachedTag> {
        return attachedTagService.getAttachedTags(contentId)
    }

    @Operation(summary = "콘텐츠 태그 상세 조회", description = "특정 콘텐츠의 특정 태그 정보를 조회합니다")
    @GetMapping("/{tagId}")
    fun getAttachedTag(
        @PathVariable contentId: Int,
        @PathVariable tagId: Int
    ): AttachedTag {
        return attachedTagService.getAttachedTag(contentId, tagId)
    }

    @Operation(summary = "콘텐츠 태그 추가", description = "콘텐츠에 새로운 태그를 연결합니다")
    @PostMapping("/add")
    fun addAttachedTag(
        @PathVariable contentId: Int,
        @RequestBody request: AttachedTagService.AttachedTagRequest
    ): AttachedTag {
        return attachedTagService.addAttachedTag(contentId, request)
    }

    @Operation(summary = "콘텐츠 태그 수정", description = "콘텐츠에 연결된 태그 정보를 수정합니다")
    @PatchMapping("/{tagId}")
    fun updateAttachedTag(
        @PathVariable contentId: Int,
        @PathVariable tagId: Int,
        @RequestBody dto: AttachedTagUpdateDto
    ): AttachedTag {
        return attachedTagService.updateAttachedTag(contentId, tagId, dto)
    }

    @Operation(summary = "콘텐츠 태그 삭제", description = "콘텐츠에서 태그 연결을 삭제합니다")
    @DeleteMapping("/{tagId}")
    fun deleteAttachedTag(
        @PathVariable contentId: Int,
        @PathVariable tagId: Int
    ): ResponseEntity<Unit> {
        attachedTagService.deleteAttachedTag(contentId, tagId)
        return ResponseEntity.ok().build()
    }
}

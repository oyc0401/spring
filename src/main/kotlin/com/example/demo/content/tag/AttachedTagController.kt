package com.example.demo.content.tag

import com.example.demo.security.UserPrincipal
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag as SwaggerTag
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@SecurityRequirement(name = "bearerAuth")
@RestController
@SwaggerTag(name = "content.tag.attached-tags-controller", description = "콘텐츠 태그 API")
@RequestMapping("/contents/{contentId}/tags")
class AttachedTagController(
    private val attachedTagService: AttachedTagService
) {

    @GetMapping("/")
    fun getAttachedTags(
        @PathVariable contentId: Int
    ): List<AttachedTag> {
        return attachedTagService.getAttachedTags(contentId)
    }

    @GetMapping("/{tagId}")
    fun getAttachedTag(
        @PathVariable contentId: Int,
        @PathVariable tagId: Int
    ): AttachedTag {
        return attachedTagService.getAttachedTag(contentId, tagId)
    }

    @PostMapping("/add")
    fun addAttachedTag(
        @PathVariable contentId: Int,
        @RequestBody request: AttachedTagService.AttachedTagRequest
    ): AttachedTag {
        return attachedTagService.addAttachedTag(contentId, request)
    }

    @PostMapping("/{tagId}/update")
    fun updateAttachedTag(
        @PathVariable contentId: Int,
        @PathVariable tagId: Int,
        @RequestBody dto: AttachedTagUpdateDto
    ): AttachedTag {
        return attachedTagService.updateAttachedTag(contentId, tagId, dto)
    }

    @DeleteMapping("/{tagId}")
    fun deleteAttachedTag(
        @PathVariable contentId: Int,
        @PathVariable tagId: Int
    ): ResponseEntity<Unit> {
        attachedTagService.deleteAttachedTag(contentId, tagId)
        return ResponseEntity.ok().build()
    }
}

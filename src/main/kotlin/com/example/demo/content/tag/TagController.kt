package com.example.demo.content.tag

import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag as SwaggerTag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@SecurityRequirement(name = "bearerAuth")
@RestController
@SwaggerTag(name = "content.tag.tag-controller", description = "태그 종류 API")
@RequestMapping("/tags")
class TagController(
    private val tagService: TagService
) {

    @GetMapping("/")
    fun getTags(): List<Tag> {
        return tagService.getTags()
    }

    @GetMapping("/{tagId}")
    fun getTag(
        @PathVariable tagId: Int
    ): Tag {
        return tagService.getTag(tagId)
    }

    @PostMapping("/add")
    fun addTag(
        @RequestBody request: TagService.TagRequest
    ): Tag {
        return tagService.addTag(request)
    }

    @PostMapping("/{tagId}/update")
    fun updateTag(
        @PathVariable tagId: Int,
        @RequestBody dto: TagUpdateDto
    ): Tag {
        return tagService.updateTag(tagId, dto)
    }

    @DeleteMapping("/{tagId}")
    fun deleteTag(
        @PathVariable tagId: Int
    ): ResponseEntity<Unit> {
        tagService.deleteTag(tagId)
        return ResponseEntity.ok().build()
    }
}

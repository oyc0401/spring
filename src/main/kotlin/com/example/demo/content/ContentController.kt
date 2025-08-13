package com.example.demo.content

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@Tag(name = "content-controller", description = "콘텐츠 API")
@RequestMapping("/content")
class ContentController(
    private val contentService: ContentService
) {

    @GetMapping("/")
    fun getContents(): List<Content> {
        return contentService.getContents()
    }

    @GetMapping("/{id}")
    fun getContent(@PathVariable id: Int): Content {
        return contentService.getContent(id)
    }

    @PostMapping("/add")
    fun addContent(@RequestBody request: ContentService.ContentRequest): Content {
        return contentService.addContent(request)
    }

    @PostMapping("/{id}/update")
    fun updateContent(
        @PathVariable id: Int,
        @RequestBody dto: ContentUpdateDto
    ): Content {
        return contentService.updateContent(id, dto)
    }

    @DeleteMapping("/{id}")
    fun deleteContent(@PathVariable id: Int): ResponseEntity<Unit> {
        contentService.deleteContent(id)
        return ResponseEntity.ok().build()
    }
}

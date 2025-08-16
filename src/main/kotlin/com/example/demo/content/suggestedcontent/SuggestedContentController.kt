package com.example.demo.content.suggestedcontent

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@Tag(name = "suggested-content-controller", description = "추천 콘텐츠 API")
@RequestMapping("/suggested-contents")
class SuggestedContentController(
    private val suggestedContentService: SuggestedContentService
) {

    @Operation(summary = "추천 콘텐츠 추가", description = "새로운 추천 콘텐츠를 추가합니다")
    @PostMapping("/add")
    fun addSuggestedContent(@RequestBody request: SuggestedContentService.SuggestedContentRequest): SuggestedContent {
        return suggestedContentService.addSuggestedContent(request)
    }

    @Operation(summary = "전체 추천 콘텐츠", description = "모든 추천 콘텐츠를 조회합니다")
    @GetMapping("/")
    fun getSuggestedContents(): List<SuggestedContent> {
        return suggestedContentService.getSuggestedContents()
    }

    @Operation(summary = "추천 콘텐츠 상세", description = "특정 추천 콘텐츠의 상세 정보를 조회합니다")
    @GetMapping("/{id}")
    fun getSuggestedContent(@PathVariable id: Int): SuggestedContent {
        return suggestedContentService.getSuggestedContent(id)
    }

//    @Operation(summary = "추천 콘텐츠 수정", description = "기존 추천 콘텐츠 정보를 수정합니다")
//    @PatchMapping("/{id}")
//    fun updateSuggestedContent(
//        @PathVariable id: Int,
//        @RequestBody dto: SuggestedContentUpdateDto
//    ): SuggestedContent {
//        return suggestedContentService.updateSuggestedContent(id, dto)
//    }

    @Operation(summary = "추천 콘텐츠 삭제", description = "추천 콘텐츠를 삭제합니다")
    @DeleteMapping("/{id}")
    fun deleteSuggestedContent(@PathVariable id: Int): ResponseEntity<Unit> {
        suggestedContentService.deleteSuggestedContent(id)
        return ResponseEntity.ok().build()
    }
}
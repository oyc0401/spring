package com.example.demo.content.pageviews

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@Tag(name = "page-views-controller", description = "페이지 조회수 API")
@RequestMapping("/page-views")
class PageViewsController(
    private val pageViewsService: PageViewsService
) {

    @Operation(summary = "페이지 조회 기록 추가", description = "새로운 페이지 조회 기록을 추가합니다")
    @PostMapping("/add")
    fun addPageViews(@RequestBody request: PageViewsService.PageViewsRequest): PageViews {
        return pageViewsService.addPageViews(request)
    }

    @Operation(summary = "전체 페이지 조회 기록", description = "모든 페이지 조회 기록을 조회합니다")
    @GetMapping("/")
    fun getPageViews(): List<PageViews> {
        return pageViewsService.getPageViews()
    }

    @Operation(summary = "페이지 조회 기록 상세", description = "특정 페이지 조회 기록의 상세 정보를 조회합니다")
    @GetMapping("/{id}")
    fun getPageViews(@PathVariable id: Int): PageViews {
        return pageViewsService.getPageViews(id)
    }

//    @Operation(summary = "페이지 조회 기록 수정", description = "기존 페이지 조회 기록 정보를 수정합니다")
//    @PatchMapping("/{id}")
//    fun updatePageViews(
//        @PathVariable id: Int,
//        @RequestBody dto: PageViewsUpdateDto
//    ): PageViews {
//        return pageViewsService.updatePageViews(id, dto)
//    }

    @Operation(summary = "페이지 조회 기록 삭제", description = "페이지 조회 기록을 삭제합니다")
    @DeleteMapping("/{id}")
    fun deletePageViews(@PathVariable id: Int): ResponseEntity<Unit> {
        pageViewsService.deletePageViews(id)
        return ResponseEntity.ok().build()
    }
}
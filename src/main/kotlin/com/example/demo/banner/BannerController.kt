package com.example.demo.banner

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@Tag(name = "banner-controller", description = "배너 API")
@RequestMapping("/banner")
class BannerController(
    private val bannerService: BannerService
) {

    @Operation(summary = "전체 배너 조회", description = "모든 배너 목록을 조회합니다")
    @GetMapping("/")
    fun getBanners(): List<Banner> {
        return bannerService.getBanners()
    }

    @Operation(summary = "활성 배너 조회", description = "현재 활성화된 배너 목록")
    @GetMapping("/active")
    fun getActiveBanners(): List<Banner> {
        return bannerService.getActiveBanners()
    }

    @Operation(summary = "현재 표시 배너 조회", description = "현재 시점에 표시되는 활성 배너를 조회합니다")
    @GetMapping("/current")
    fun getCurrentActiveBanners(): List<Banner> {
        return bannerService.getCurrentActiveBanners()
    }

    @Operation(summary = "배너 상세 조회", description = "특정 배너의 상세 정보를 조회합니다")
    @GetMapping("/{id}")
    fun getBanner(@PathVariable id: Int): Banner {
        return bannerService.getBanner(id)
    }

    @Operation(summary = "배너 생성", description = "새로운 배너를 생성합니다")
    @PostMapping("/add")
    fun addBanner(@RequestBody request: BannerService.BannerRequest): Banner {
        return bannerService.addBanner(request)
    }

    @Operation(summary = "배너 수정", description = "기존 배너 정보를 수정합니다")
    @PostMapping("/{id}/update")
    fun updateBanner(
        @PathVariable id: Int,
        @RequestBody dto: BannerUpdateDto
    ): Banner {
        return bannerService.updateBanner(id, dto)
    }

    @Operation(summary = "배너 삭제", description = "특정 배너를 삭제합니다")
    @DeleteMapping("/{id}")
    fun deleteBanner(@PathVariable id: Int): ResponseEntity<Unit> {
        bannerService.deleteBanner(id)
        return ResponseEntity.ok().build()
    }
}
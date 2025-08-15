package com.example.demo.banner

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@Tag(name = "banner-controller", description = "배너 API")
@RequestMapping("/banner")
class BannerController(
    private val bannerService: BannerService
) {

    @GetMapping("/")
    fun getBanners(): List<Banner> {
        return bannerService.getBanners()
    }

    @GetMapping("/active")
    fun getActiveBanners(): List<Banner> {
        return bannerService.getActiveBanners()
    }

    @GetMapping("/current")
    fun getCurrentActiveBanners(): List<Banner> {
        return bannerService.getCurrentActiveBanners()
    }

    @GetMapping("/{id}")
    fun getBanner(@PathVariable id: Int): Banner {
        return bannerService.getBanner(id)
    }

    @PostMapping("/add")
    fun addBanner(@RequestBody request: BannerService.BannerRequest): Banner {
        return bannerService.addBanner(request)
    }

    @PostMapping("/{id}/update")
    fun updateBanner(
        @PathVariable id: Int,
        @RequestBody dto: BannerUpdateDto
    ): Banner {
        return bannerService.updateBanner(id, dto)
    }

    @DeleteMapping("/{id}")
    fun deleteBanner(@PathVariable id: Int): ResponseEntity<Unit> {
        bannerService.deleteBanner(id)
        return ResponseEntity.ok().build()
    }
}
package com.example.demo.banner

import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.NoSuchElementException

@Service
class BannerService(
    private val bannerRepository: BannerRepository,
    private val mapper: BannerMapper
) {
    data class BannerRequest(
        val title: String,
        val imageUrl: String,
        val linkUrl: String? = null,
        val description: String? = null,
        val isActive: Boolean = true,
        val startDate: LocalDate? = null,
        val endDate: LocalDate? = null,
        val priority: Int = 0
    )

    fun addBanner(request: BannerRequest): Banner {
        val banner = Banner(
            title = request.title,
            imageUrl = request.imageUrl,
            linkUrl = request.linkUrl,
            description = request.description,
            isActive = request.isActive,
            startDate = request.startDate,
            endDate = request.endDate,
            priority = request.priority
        )

        return bannerRepository.save(banner)
    }

    fun getBanners(): List<Banner> {
        return bannerRepository.findAll()
    }

    fun getActiveBanners(): List<Banner> {
        return bannerRepository.findActiveByPriorityDesc()
    }

    fun getCurrentActiveBanners(): List<Banner> {
        return bannerRepository.findActiveAndCurrentByPriorityDesc()
    }

    fun getBanner(bannerId: Int): Banner {
        return bannerRepository.findById(bannerId)
            .orElseThrow { NoSuchElementException("Banner not found") }
    }

    fun updateBanner(bannerId: Int, dto: BannerUpdateDto): Banner {
        val banner = bannerRepository.findById(bannerId)
            .orElseThrow { NoSuchElementException("Banner not found") }

        mapper.partialUpdate(banner, dto)
        return banner
    }

    fun deleteBanner(bannerId: Int) {
        val banner = bannerRepository.findById(bannerId)
            .orElseThrow { NoSuchElementException("Banner not found") }

        bannerRepository.delete(banner)
    }
}
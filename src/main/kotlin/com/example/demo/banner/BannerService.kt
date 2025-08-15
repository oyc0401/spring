package com.example.demo.banner

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
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
        val endDate: LocalDate? = null
    )

    data class SwapPriorityRequest(
        val bannerId1: Int,
        val bannerId2: Int
    )

    fun addBanner(request: BannerRequest): Banner {
        // 자동으로 최대 priority + 1 할당
        val maxPriority = bannerRepository.findMaxPriority()
        val banner = Banner(
            title = request.title,
            imageUrl = request.imageUrl,
            linkUrl = request.linkUrl,
            description = request.description,
            isActive = request.isActive,
            startDate = request.startDate,
            endDate = request.endDate,
            priority = maxPriority + 1
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

    @Transactional
    fun swapBannerPriority(bannerId1: Int, bannerId2: Int) {
        val banner1 = bannerRepository.findById(bannerId1)
            .orElseThrow { NoSuchElementException("Banner with id $bannerId1 not found") }
        val banner2 = bannerRepository.findById(bannerId2)
            .orElseThrow { NoSuchElementException("Banner with id $bannerId2 not found") }

        // 두 배너의 priority 교체
        val tempPriority = banner1.priority
        banner1.priority = banner2.priority
        banner2.priority = tempPriority
        
        // JPA dirty checking으로 자동 저장
    }
}
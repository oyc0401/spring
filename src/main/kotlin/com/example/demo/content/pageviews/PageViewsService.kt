package com.example.demo.content.pageviews

import org.springframework.stereotype.Service
import java.util.NoSuchElementException

@Service
class PageViewsService(
    private val pageViewsRepository: PageViewsRepository,
    private val mapper: PageViewsMapper
) {
    data class PageViewsRequest(
        val contentId: Int,
        val userId: Int
    )

    fun addPageViews(request: PageViewsRequest): PageViews {
        val pageViews = PageViews(
            contentId = request.contentId,
            userId = request.userId
        )
        return pageViewsRepository.save(pageViews)
    }

    fun getPageViews(): List<PageViews> {
        return pageViewsRepository.findAll()
    }

    fun getPageViews(id: Int): PageViews {
        return pageViewsRepository.findById(id)
            .orElseThrow { NoSuchElementException("PageViews not found") }
    }

    fun updatePageViews(id: Int, dto: PageViewsUpdateDto): PageViews {
        val pageViews = pageViewsRepository.findById(id)
            .orElseThrow { NoSuchElementException("PageViews not found") }

        mapper.partialUpdate(pageViews, dto)
        return pageViews
    }

    fun deletePageViews(id: Int) {
        pageViewsRepository.deleteById(id)
    }
}
package com.example.demo.content

import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.NoSuchElementException

@Service
class ContentService(
    private val contentRepository: ContentRepository,
    private val mapper: ContentMapper
) {
    data class ContentRequest(
        val title: String,
        val bannerUrl: String,
        val writer: String,
        val companyType: String,
        val startTime: String,
        val endTime: String,
        val type: String,
        val viewCount: Int = 0,
        val bookmarkCount: Int = 0,
    )

    fun addContent(request: ContentRequest): Content {
        val content = Content(
            title = request.title,
            bannerUrl = request.bannerUrl,
            writer = request.writer,
            companyType = request.companyType,
            startTime = request.startTime,
            endTime = request.endTime,
            type = request.type,
            viewCount = request.viewCount,
            bookmarkCount = request.bookmarkCount,
        )

        return contentRepository.save(content)
    }

    fun getContents(): List<Content> {
        return contentRepository.findByIsDeletedFalseAndIsActiveTrueOrderByCreatedAtDesc()
    }

    fun getFeaturedContents(): List<Content> {
        return contentRepository.findByIsDeletedFalseAndIsActiveTrueAndIsFeaturedTrueOrderByCreatedAtDesc()
    }

    fun getContentsByType(type: String): List<Content> {
        return contentRepository.findByIsDeletedFalseAndIsActiveTrueAndTypeOrderByCreatedAtDesc(type)
    }

    fun getContent(contentId: Int): Content {
        return contentRepository.findActiveById(contentId)
            ?: throw NoSuchElementException("Content not found")
    }

    fun updateContent(contentId: Int, dto: ContentUpdateDto): Content {
        val content = contentRepository.findActiveById(contentId)
            ?: throw NoSuchElementException("Content not found")

        content.updatedAt = LocalDateTime.now()
        mapper.partialUpdate(content, dto)
        return content // JPA dirty checking으로 자동 저장
    }

    fun deleteContent(contentId: Int) {
        val content = contentRepository.findActiveById(contentId)
            ?: throw NoSuchElementException("Content not found")

        // 하드 삭제 - 데이터베이스에서 완전히 제거
        contentRepository.delete(content)
    }

    fun softDeleteContent(contentId: Int) {
        val content = contentRepository.findActiveById(contentId)
            ?: throw NoSuchElementException("Content not found")

        content.isDeleted = true
        content.updatedAt = LocalDateTime.now()
        // 소프트 삭제 - JPA dirty checking으로 자동 저장
    }
}

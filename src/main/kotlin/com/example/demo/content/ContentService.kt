package com.example.demo.content

import org.springframework.stereotype.Service
import java.util.NoSuchElementException

@Service
class ContentService(
    private val contentRepository: ContentRepository,
    private val mapper: ContentMapper
) {
    data class ContentRequest(
        val viewCount: Int = 0,
        val scrapCount: Int = 0
    )

    fun addContent(request: ContentRequest): Content {
        val content = Content(
            viewCount = request.viewCount,
            scrapCount = request.scrapCount
        )

        return contentRepository.save(content)
    }

    fun getContents(): List<Content> {
        return contentRepository.findAll()
    }

    fun getContent(contentId: Int): Content {
        return contentRepository.findById(contentId)
            .orElseThrow { NoSuchElementException("Content not found") }
    }

    fun updateContent(contentId: Int, dto: ContentUpdateDto): Content {
        val content = contentRepository.findById(contentId)
            .orElseThrow { NoSuchElementException("Content not found") }

        mapper.partialUpdate(content, dto)
        return content // JPA dirty checking으로 자동 저장
    }

    fun deleteContent(contentId: Int) {
        val content = contentRepository.findById(contentId)
            .orElseThrow { NoSuchElementException("Content not found") }

        contentRepository.delete(content)
    }
}

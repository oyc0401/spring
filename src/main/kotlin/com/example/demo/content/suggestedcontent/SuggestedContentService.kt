package com.example.demo.content.suggestedcontent

import org.springframework.stereotype.Service
import java.util.NoSuchElementException

@Service
class SuggestedContentService(
    private val suggestedContentRepository: SuggestedContentRepository,
    private val mapper: SuggestedContentMapper
) {
    data class SuggestedContentRequest(
        val userId: Int,
        val contentId: Int,
        val type: String
    )

    fun addSuggestedContent(request: SuggestedContentRequest): SuggestedContent {
        val suggestedContent = SuggestedContent(
            userId = request.userId,
            contentId = request.contentId,
            type = request.type
        )
        return suggestedContentRepository.save(suggestedContent)
    }

    fun getSuggestedContents(): List<SuggestedContent> {
        return suggestedContentRepository.findAll()
    }

    fun getSuggestedContent(id: Int): SuggestedContent {
        return suggestedContentRepository.findById(id)
            .orElseThrow { NoSuchElementException("SuggestedContent not found") }
    }

    fun updateSuggestedContent(id: Int, dto: SuggestedContentUpdateDto): SuggestedContent {
        val suggestedContent = suggestedContentRepository.findById(id)
            .orElseThrow { NoSuchElementException("SuggestedContent not found") }

        mapper.partialUpdate(suggestedContent, dto)
        return suggestedContent
    }

    fun deleteSuggestedContent(id: Int) {
        suggestedContentRepository.deleteById(id)
    }
}
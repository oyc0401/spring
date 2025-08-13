package com.example.demo.user.bookmarkedContents

import org.springframework.stereotype.Service
import java.util.NoSuchElementException

@Service
class BookmarkedContentService(
    private val bookmarkedContentRepository: BookmarkedContentRepository,
    private val mapper: BookmarkedContentMapper
) {
    data class BookmarkedContentRequest(
        val contentId: Int
    )

    fun addBookmarkedContent(userId: Int, request: BookmarkedContentRequest): BookmarkedContent {
        val bookmarkedContent = BookmarkedContent(
            id = BookmarkedContentId(userId = userId, contentId = request.contentId)
        )

        return bookmarkedContentRepository.save(bookmarkedContent)
    }

    fun getBookmarkedContents(userId: Int): List<BookmarkedContent> {
        return bookmarkedContentRepository.findByUserId(userId)
    }

    fun getBookmarkedContent(userId: Int, contentId: Int): BookmarkedContent {
        return bookmarkedContentRepository.findByUserIdAndContentId(userId, contentId)
            ?: throw NoSuchElementException("BookmarkedContent not found")
    }

    fun updateBookmarkedContent(userId: Int, contentId: Int, dto: BookmarkedContentUpdateDto): BookmarkedContent {
        val bookmarkedContent = bookmarkedContentRepository.findByUserIdAndContentId(userId, contentId)
            ?: throw NoSuchElementException("BookmarkedContent not found")

        mapper.partialUpdate(bookmarkedContent, dto)
        return bookmarkedContent // JPA dirty checking으로 자동 저장
    }

    fun deleteBookmarkedContent(userId: Int, contentId: Int) {
        val bookmarkedContent = bookmarkedContentRepository.findByUserIdAndContentId(userId, contentId)
            ?: throw NoSuchElementException("BookmarkedContent not found")

        bookmarkedContentRepository.deleteByUserIdAndContentId(userId, contentId)
    }
}

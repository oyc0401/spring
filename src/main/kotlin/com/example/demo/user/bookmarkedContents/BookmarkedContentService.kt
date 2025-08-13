package com.example.demo.user.bookmarkedContents

import org.springframework.stereotype.Service
import java.util.NoSuchElementException

@Service
class BookmarkedContentService(
    private val bookmarkedContentRepository: BookmarkedContentRepository,
    private val mapper: BookmarkedContentMapper
) {
    data class BookmarkedContentRequest(
        val title: String,
        val details: String,
    )

    fun addBookmarkedContent(userId: Int, request: BookmarkedContentRequest): BookmarkedContent {
        val bookmarkedContent = BookmarkedContent(
            userId = userId,
            title = request.title,
            details = request.details
        )

        return bookmarkedContentRepository.save(bookmarkedContent)
    }

    fun getBookmarkedContents(userId: Int): List<BookmarkedContent> {
        return bookmarkedContentRepository.findByUserId(userId)
    }

    fun getBookmarkedContent(userId: Int, bookmarkedContentId: Int): BookmarkedContent {
        val bookmarkedContent = bookmarkedContentRepository.findById(bookmarkedContentId)
            .orElseThrow { NoSuchElementException("BookmarkedContent not found") }

        if (bookmarkedContent.userId != userId) {
            throw IllegalArgumentException("Access denied: BookmarkedContent does not belong to user")
        }

        return bookmarkedContent
    }

    fun updateBookmarkedContent(userId: Int, bookmarkedContentId: Int, dto: BookmarkedContentUpdateDto): BookmarkedContent {
        val bookmarkedContent = bookmarkedContentRepository.findById(bookmarkedContentId)
            .orElseThrow { NoSuchElementException("BookmarkedContent not found") }

        if (bookmarkedContent.userId != userId) {
            throw IllegalArgumentException("Access denied: BookmarkedContent does not belong to user")
        }

        mapper.partialUpdate(bookmarkedContent, dto)
        return bookmarkedContent // JPA dirty checking으로 자동 저장
    }

    fun deleteBookmarkedContent(userId: Int, bookmarkedContentId: Int) {
        val bookmarkedContent = bookmarkedContentRepository.findById(bookmarkedContentId)
            .orElseThrow { NoSuchElementException("BookmarkedContent not found") }

        if (bookmarkedContent.userId != userId) {
            throw IllegalArgumentException("Access denied: BookmarkedContent does not belong to user")
        }

        bookmarkedContentRepository.delete(bookmarkedContent)
    }
}

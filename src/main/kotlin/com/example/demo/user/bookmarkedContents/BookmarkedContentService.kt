package com.example.demo.user.bookmarkedContents

import org.springframework.stereotype.Service
import java.util.NoSuchElementException

@Service
class BookmarkedContentService(
    private val bookmarkedContentRepository: BookmarkedContentRepository
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
        return bookmarkedContentRepository.findAllByIdUserId(userId)
    }


    fun deleteBookmarkedContent(userId: Int, contentId: Int) {
        val bookmarkedContent =
            bookmarkedContentRepository.findById(BookmarkedContentId(userId = userId, contentId = contentId))
                .orElseThrow { NoSuchElementException("BookmarkedContent not found") }

        bookmarkedContentRepository.deleteById(BookmarkedContentId(userId = userId, contentId = contentId))
    }
}

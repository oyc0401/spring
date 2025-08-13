package com.example.demo.user.bookmarkedContents

import org.springframework.data.jpa.repository.JpaRepository

interface BookmarkedContentRepository : JpaRepository<BookmarkedContent, BookmarkedContentId> {

    fun findAllByIdUserId(userId: Int): List<BookmarkedContent>
}

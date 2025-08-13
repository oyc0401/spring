package com.example.demo.user.bookmarkedContents

import org.springframework.data.jpa.repository.JpaRepository

interface BookmarkedContentRepository : JpaRepository<BookmarkedContent, Int> {

    fun findByUserId(userId: Int): List<BookmarkedContent>
}

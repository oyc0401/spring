package com.example.demo.user.bookmarkedContents

import org.springframework.data.jpa.repository.JpaRepository

interface BookmarkedContentRepository : JpaRepository<BookmarkedContent, BookmarkedContentId> {

    fun findByUserId(userId: Int): List<BookmarkedContent>
    
    fun findByUserIdAndContentId(userId: Int, contentId: Int): BookmarkedContent?
    
    fun deleteByUserIdAndContentId(userId: Int, contentId: Int)
}

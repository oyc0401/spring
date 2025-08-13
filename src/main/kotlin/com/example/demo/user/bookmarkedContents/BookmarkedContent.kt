package com.example.demo.user.bookmarkedContents

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(
    name = "bookmarked_contents"
)
data class BookmarkedContent(
    @EmbeddedId
    var id: BookmarkedContentId = BookmarkedContentId(),

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    var createdAt: LocalDateTime? = null
)

package com.example.demo.content.suggestedcontent

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "suggested_contents")
data class SuggestedContent(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(name = "user_id", nullable = false)
    var userId: Int = 0,

    @Column(name = "content_id", nullable = false)
    var contentId: Int = 0,

    @Column(name = "type", nullable = false, length = 100)
    var type: String = "",

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    var createdAt: LocalDateTime? = null
)
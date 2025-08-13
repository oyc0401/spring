package com.example.demo.content

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "contents")
data class Content(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    var viewCount: Int = 0,

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    var scrapCount: Int = 0,

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    var createdAt: LocalDateTime? = null
)

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

    @Column(name = "type", nullable = false)
    var type: String = "",

    @Column(name = "title", nullable = false)
    var title: String = "",

    @Column(name = "banner_url", nullable = false, length = 500)
    var bannerUrl: String = "",

    @Column(name = "writer", nullable = false, length = 100)
    var writer: String = "",

    @Column(name = "company_type", nullable = false, length = 100) // 기업형태
    var companyType: String = "",

    @Column(name = "start_time", nullable = false)
    var startTime: String = "",

    @Column(name = "end_time", nullable = false)
    var endTime: String = "",

    @Column(name = "view_count", nullable = false, columnDefinition = "INT DEFAULT 0")
    var viewCount: Int = 0,

    @Column(name = "bookmark_count", nullable = false, columnDefinition = "INT DEFAULT 0")
    var bookmarkCount: Int = 0,

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    var createdAt: LocalDateTime? = null
)

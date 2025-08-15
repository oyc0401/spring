package com.example.demo.banner

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "banners")
data class Banner(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(name = "title", nullable = false, length = 200)
    var title: String = "",

    @Column(name = "image_url", nullable = false, length = 500)
    var imageUrl: String = "",

    @Column(name = "link_url", nullable = true, length = 500)
    var linkUrl: String? = null,

    @Column(name = "description", nullable = true, columnDefinition = "TEXT")
    var description: String? = null,

    @Column(name = "is_active", nullable = false)
    var isActive: Boolean = true,

    @Column(name = "start_date", nullable = true)
    var startDate: LocalDate? = null,

    @Column(name = "end_date", nullable = true)
    var endDate: LocalDate? = null,

    @Column(name = "priority", nullable = false)
    var priority: Int = 0,

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    var createdAt: LocalDateTime? = null,

    @LastModifiedDate
    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = null
)
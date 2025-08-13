package com.example.demo.user.recentSearch

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(
    name = "recent_searches",
    indexes = [
        Index(name = "idx_recent_search_user", columnList = "user_id"),
    ]
)
class RecentSearch(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(name = "user_id", nullable = false)
    var userId: Int = 0, // 조인 안 함. FK는 DB에서만 관리

    @Column(nullable = false, length = 120)
    var title: String = "",

    @Lob
    @Column(name = "details")
    var details: String? = null,

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    var createdAt: LocalDateTime? = null
)

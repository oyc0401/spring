package com.example.demo.user.activity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(
    name = "activities",
    indexes = [
        Index(name = "idx_activity_user", columnList = "user_id"),
        Index(name = "idx_activity_priority", columnList = "priority")
    ]
)
data class Activity(
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

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    var priority: Int = 0,

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    var createdAt: LocalDateTime? = null
)

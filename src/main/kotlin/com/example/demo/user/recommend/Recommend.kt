package com.example.demo.user.recommend

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(
    name = "recommends"
)
class Recommend(
    @EmbeddedId
    var id: RecommendId = RecommendId(),

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    var view: Boolean = false,

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    var createdAt: LocalDateTime? = null
)

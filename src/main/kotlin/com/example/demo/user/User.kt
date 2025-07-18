package com.example.demo.user

import com.example.demo.auth.Auth
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(nullable = false)
    var name: String = "",

    @CreatedDate
    @Column(name = "created_at")
    var createdAt: LocalDateTime? = null,

)


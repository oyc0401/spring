package com.example.demo.user

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(unique = true)
    var email: String? = null,
    var password: String? = null,
    @Column(name = "oauth_id", unique = true)
    var oauthId: String? = null,
    @Column(name = "login_provider")
    val loginProvider: String? = null,

    var name: String? = null,

    @CreatedDate
    @Column(name = "created_at")
    var createdAt: LocalDateTime? = null,

    )


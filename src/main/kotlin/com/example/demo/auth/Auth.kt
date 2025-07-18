package com.example.demo.auth

import jakarta.persistence.*

@Entity
@Table(name = "auth")
data class Auth(
    @Id
    @Column(name = "user_id")
    val userId: Int = 0,

    @Column(unique = true)
    var email: String? = null,

    var password: String? = null,

    @Column(name = "oauth_id", unique = true)
    var oauthId: String? = null,

    @Column(name = "login_provider", nullable = false)
    var loginProvider: String = "",

    @Column(name = "user_role", nullable = false)
    var role: String = ""
)

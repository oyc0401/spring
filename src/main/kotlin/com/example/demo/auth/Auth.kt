package com.example.demo.auth

import com.example.demo.user.User
import jakarta.persistence.*

@Entity
@Table(name = "auth")
data class Auth(
    @Id
    @Column(name = "user_id", insertable = false, updatable = false)
    val userId: Int = 0,

    // user <- auth
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(
        name = "user_id",
        foreignKey = ForeignKey(
            name = "fk_auth_user",
            foreignKeyDefinition = "FOREIGN KEY (id) REFERENCES users(id) ON DELETE CASCADE"
        )
    )
    val user: User? = null,

    @Column(unique = true)
    var username: String? = null,

    var password: String? = null,

    @Column(name = "oauth_id", unique = true)
    var oauthId: String? = null,

    @Column(name = "login_provider", nullable = false)
    var loginProvider: String = "",

    @Column(name = "user_role", nullable = false)
    var role: String = ""
)

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

    var email: String? = null,

    var school: String? = null,

    @Column(name = "school_status")
    var schoolStatus: String? = null,

    @Column(name = "grade")
    var grade: String? = null, // 성적

    @Column(name = "is_grade_public", nullable = false, columnDefinition = "Boolean DEFAULT 0")
    var isGradePublic: Boolean = false, // 성적 공개 여부

    @Column(name = "residence_city")
    var residenceCity: String? = null, // 거주지

    @Column(name = "residence_country")
    var residenceCountry: String? = null, // 거주국가

    @Column(name = "is_residence_public", nullable = false, columnDefinition = "Boolean DEFAULT 0")
    var isResidencePublic: Boolean = false, // 거주지 공개 여부

    @Column(name = "bio", length = 255)
    var bio: String? = null, // 한 줄 소개

    @Column(name = "vision", length = 500)
    var vision: String? = null, // 비전

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    var deleted: Boolean = false,

    @Column(name = "deleted_time")
    var deletedTime: LocalDateTime? = null
)


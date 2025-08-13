package com.example.demo.content.contest

import com.example.demo.content.Content
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table(name = "contests")
@EntityListeners(AuditingEntityListener::class)
data class Contest(

    // contests.content_id = contents.id (PK = FK, 공유 PK)
    @Id
    @Column(name = "content_id", insertable = false, updatable = false)
    val contentId: Int = 0,

    // Content 삭제 시 DB에서 Contest 자동 삭제 (ON DELETE CASCADE)
    // "삭제만" 연동을 위해 JPA Cascade는 지정하지 않고, DB 레벨로만 처리합니다.
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "content_id", referencedColumnName = "id", nullable = false)
    @MapsId
    @OnDelete(action = OnDeleteAction.CASCADE)
    var content: Content? = null,

    @Column(name = "banner_url", length = 500)
    var bannerUrl: String? = null,

    @Column(name = "title", nullable = false, length = 200)
    var title: String = "",

    @Column(name = "subtitle", length = 200)
    var subtitle: String? = null,

    @Column(name = "writer", length = 100)
    var writer: String? = null,

    @Column(name = "company_type", length = 100) // 기업형태
    var companyType: String? = null,

    @Column(name = "target_participants", length = 200) // 참여대상
    var targetParticipants: String? = null,

    @Column(name = "start_time")
    var startTime: LocalDateTime? = null,

    @Column(name = "end_time")
    var endTime: LocalDateTime? = null,

    @Column(name = "extra_info", columnDefinition = "TEXT") // 추가정보
    var extraInfo: String? = null,

    @Column(name = "description", columnDefinition = "TEXT") // 상세
    var description: String? = null,

    @Column(name = "website_url", length = 500) // 사이트 주소
    var websiteUrl: String? = null,

    @Column(name = "contact", length = 200)
    var contact: String? = null,

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    var createdAt: LocalDateTime? = null,

    @LastModifiedDate
    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = null
)
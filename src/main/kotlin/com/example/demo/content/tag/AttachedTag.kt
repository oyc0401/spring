package com.example.demo.content.tag

import jakarta.persistence.*

@Entity
@Table(name = "attached_tags")
class AttachedTag(

    // 복합 PK (contentId + tagId)
    @EmbeddedId
    val id: AttachedTagKey,

    // tag만 자동 조인 + cascade 적용 (content는 조인 안 함)
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @MapsId("tagId") // ← id.tagId와 이 연관관계를 같은 컬럼으로 매핑
    @JoinColumn(name = "tag_id", nullable = false)
    val tag: Tag,

    @Column(nullable = false)
    val name: String = ""
)
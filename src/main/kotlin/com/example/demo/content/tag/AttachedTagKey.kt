package com.example.demo.content.tag

import jakarta.persistence.*

import java.io.Serializable

@Embeddable
data class AttachedTagKey(
    @Column(name = "content_id", nullable = false)
    val contentId: Int = 0,

    @Column(name = "tag_id", nullable = false)
    val tagId: Int = 0
) : Serializable
package com.example.demo.content.tag

import jakarta.persistence.*

import java.io.Serializable

@Embeddable
data class AttachedTagKey(
    @Column(name = "content_id", nullable = false)
    val contentId: Long = 0,

    @Column(name = "tag_id", nullable = false)
    val tagId: Long = 0
) : Serializable
package com.example.demo.content.tag

import org.springframework.data.jpa.repository.JpaRepository

interface AttachedTagRepository : JpaRepository<AttachedTag, AttachedTagKey> {

    fun findAllByIdContentId(contentId: Int): List<AttachedTag>
}

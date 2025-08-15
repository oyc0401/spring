package com.example.demo.content.tagType

import org.springframework.data.jpa.repository.JpaRepository

interface TagRepository : JpaRepository<TagType, Int> {
}

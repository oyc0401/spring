package com.example.demo.content.tag

import org.springframework.stereotype.Service
import java.util.NoSuchElementException

@Service
class TagService(
    private val tagRepository: TagRepository,
    private val mapper: TagMapper
) {
    data class TagRequest(
        val tagName: String
    )

    fun addTag(request: TagRequest): Tag {
        val tag = Tag(tagName = request.tagName)
        return tagRepository.save(tag)
    }

    fun getTags(): List<Tag> {
        return tagRepository.findAll()
    }

    fun getTag(tagId: Int): Tag {
        return tagRepository.findById(tagId)
            .orElseThrow { NoSuchElementException("Tag not found") }
    }

    fun updateTag(tagId: Int, dto: TagUpdateDto): Tag {
        val tag = tagRepository.findById(tagId)
            .orElseThrow { NoSuchElementException("Tag not found") }

        mapper.partialUpdate(tag, dto)
        return tag // JPA dirty checking 자동 저장
    }

    fun deleteTag(tagId: Int) {
        val tag = tagRepository.findById(tagId)
            .orElseThrow { NoSuchElementException("Tag not found") }

        tagRepository.delete(tag)
    }
}

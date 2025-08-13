package com.example.demo.content.tag

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AttachedTagService(
    private val attachedTagRepository: AttachedTagRepository,
    private val mapper: AttachedTagMapper
) {

    fun getAttachedTags(contentId: Int): List<AttachedTag> {
        return attachedTagRepository.findAllByIdContentId(contentId)
    }

    fun getAttachedTag(contentId: Int, tagId: Int): AttachedTag {
        return attachedTagRepository.findById(AttachedTagKey(contentId, tagId))
            .orElseThrow { NoSuchElementException("AttachedTag not found") }
    }

    @Transactional
    fun addAttachedTag(contentId: Int, request: AttachedTagRequest): AttachedTag {
        val attachedTag = AttachedTag(
            id = AttachedTagKey(contentId, request.tagId),
            tag = request.tag,
            name = request.name
        )
        return attachedTagRepository.save(attachedTag)
    }

    @Transactional
    fun updateAttachedTag(contentId: Int, tagId: Int, dto: AttachedTagUpdateDto): AttachedTag {
        val attachedTag = getAttachedTag(contentId, tagId)
        mapper.partialUpdate(attachedTag, dto)
        return attachedTag
    }

    @Transactional
    fun deleteAttachedTag(contentId: Int, tagId: Int) {
        attachedTagRepository.deleteById(AttachedTagKey(contentId, tagId))
    }

    data class AttachedTagRequest(
        val tagId: Int,
        val tag: Tag,
        val name: String
    )
}

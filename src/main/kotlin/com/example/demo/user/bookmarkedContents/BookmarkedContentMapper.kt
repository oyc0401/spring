package com.example.demo.user.bookmarkedContents

import com.example.demo.user.ifPresent
import org.mapstruct.*
import org.springframework.stereotype.Component

@Mapper(componentModel = "spring")
abstract class BookmarkedContentMapper {

    /**
     * 부분 업데이트: DTO에서 isPresent인 필드만 엔티티에 반영
     * Note: BookmarkedContent has no updatable fields currently
     */
    @BeanMapping(ignoreByDefault = true)
    abstract fun partialUpdate(@MappingTarget bookmarkedContent: BookmarkedContent, dto: BookmarkedContentUpdateDto)

    /**
     * JsonNullable 처리는 @AfterMapping에서 일괄 적용
     * Note: Currently no fields to update
     */
    @AfterMapping
    protected fun after(@MappingTarget bookmarkedContent: BookmarkedContent, dto: BookmarkedContentUpdateDto) {
        // No fields to update for bookmarked content
        // userId and contentId are part of composite primary key
        // createdAt is auto-generated
    }
}

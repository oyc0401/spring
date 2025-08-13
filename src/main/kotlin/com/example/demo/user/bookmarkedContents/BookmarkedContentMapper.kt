package com.example.demo.user.bookmarkedContents

import com.example.demo.user.ifPresent
import org.mapstruct.*
import org.springframework.stereotype.Component

@Mapper(componentModel = "spring")
abstract class BookmarkedContentMapper {

    /**
     * 부분 업데이트: DTO에서 isPresent인 필드만 엔티티에 반영
     */
    @BeanMapping(ignoreByDefault = true)
    abstract fun partialUpdate(@MappingTarget bookmarkedContent: BookmarkedContent, dto: BookmarkedContentUpdateDto)

    /**
     * JsonNullable 처리는 @AfterMapping에서 일괄 적용
     */
    @AfterMapping
    protected fun after(@MappingTarget bookmarkedContent: BookmarkedContent, dto: BookmarkedContentUpdateDto) {
        dto.title.ifPresent { it?.let { v -> bookmarkedContent.title = v } }
        dto.details.ifPresent { bookmarkedContent.details = it }
    }
}

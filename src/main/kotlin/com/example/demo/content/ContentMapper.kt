package com.example.demo.content

import com.example.demo.user.ifPresent
import org.mapstruct.*
import org.springframework.stereotype.Component

@Mapper(componentModel = "spring")
abstract class ContentMapper {

    /**
     * 부분 업데이트: DTO에서 isPresent인 필드만 엔티티에 반영
     */
    @BeanMapping(ignoreByDefault = true)
    abstract fun partialUpdate(@MappingTarget content: Content, dto: ContentUpdateDto)

    /**
     * JsonNullable 처리는 @AfterMapping에서 일괄 적용
     */
    @AfterMapping
    protected fun after(@MappingTarget content: Content, dto: ContentUpdateDto) {
        dto.viewCount.ifPresent { content.viewCount = it }
        dto.scrapCount.ifPresent { content.scrapCount = it }
    }
}

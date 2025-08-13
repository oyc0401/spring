package com.example.demo.content

import com.example.demo.user.ifPresent
import org.mapstruct.*

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
        dto.title.ifPresent { it?.let { v -> content.title = v } }
        dto.bannerUrl.ifPresent { it?.let { v -> content.bannerUrl = v } }
        dto.writer.ifPresent { it?.let { v -> content.writer = v } }
        dto.companyType.ifPresent { it?.let { v -> content.companyType = v } }
        dto.startTime.ifPresent { it?.let { v -> content.startTime = v } }
        dto.endTime.ifPresent { it?.let { v -> content.endTime = v } }
        dto.viewCount.ifPresent { content.viewCount = it }
        dto.bookmarkCount.ifPresent { content.bookmarkCount = it }
    }
}

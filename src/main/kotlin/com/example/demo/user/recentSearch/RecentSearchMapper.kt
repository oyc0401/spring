package com.example.demo.user.recentSearch

import com.example.demo.user.ifPresent
import org.mapstruct.*
import org.springframework.stereotype.Component

@Mapper(componentModel = "spring")
abstract class RecentSearchMapper {

    /**
     * 부분 업데이트: DTO에서 isPresent인 필드만 엔티티에 반영
     */
    @BeanMapping(ignoreByDefault = true)
    abstract fun partialUpdate(@MappingTarget recentSearch: RecentSearch, dto: RecentSearchUpdateDto)

    /**
     * JsonNullable 처리는 @AfterMapping에서 일괄 적용
     */
    @AfterMapping
    protected fun after(@MappingTarget recentSearch: RecentSearch, dto: RecentSearchUpdateDto) {
        dto.text.ifPresent { it?.let { v -> recentSearch.text = v } }
    }
}

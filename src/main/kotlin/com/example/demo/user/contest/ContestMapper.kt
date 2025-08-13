package com.example.demo.user.contest

import com.example.demo.user.ifPresent
import org.mapstruct.*
import org.springframework.stereotype.Component

@Mapper(componentModel = "spring")
abstract class ContestMapper {

    /**
     * 부분 업데이트: DTO에서 isPresent인 필드만 엔티티에 반영
     */
    @BeanMapping(ignoreByDefault = true)
    abstract fun partialUpdate(@MappingTarget contest: Contest, dto: ContestUpdateDto)

    /**
     * JsonNullable 처리는 @AfterMapping에서 일괄 적용
     */
    @AfterMapping
    protected fun after(@MappingTarget contest: Contest, dto: ContestUpdateDto) {
        dto.title.ifPresent { it?.let { v -> contest.title = v } }
        dto.details.ifPresent { contest.details = it }
    }
}

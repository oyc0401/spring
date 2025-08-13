package com.example.demo.user.activity

import com.example.demo.user.ifPresent
import org.mapstruct.*
import org.springframework.stereotype.Component

@Mapper(componentModel = "spring")
abstract class ActivityMapper {

    /**
     * 부분 업데이트: DTO에서 isPresent인 필드만 엔티티에 반영
     */
    @BeanMapping(ignoreByDefault = true)
    abstract fun partialUpdate(@MappingTarget activity: Activity, dto: ActivityUpdateDto)

    /**
     * JsonNullable 처리는 @AfterMapping에서 일괄 적용
     */
    @AfterMapping
    protected fun after(@MappingTarget activity: Activity, dto: ActivityUpdateDto) {
        dto.title.ifPresent { it?.let { v -> activity.title = v } }
        dto.details.ifPresent { activity.details = it }
    }
}

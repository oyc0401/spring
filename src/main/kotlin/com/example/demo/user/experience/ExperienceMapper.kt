
package com.example.demo.user.experience

import org.mapstruct.*

@Mapper(componentModel = "spring")
abstract class ExperienceMapper {

    /**
     * 부분 업데이트: DTO에서 isPresent인 필드만 엔티티에 반영
     */
    @BeanMapping(ignoreByDefault = true)
    abstract fun partialUpdate(@MappingTarget experience: Experience, dto: ExperienceUpdateDto)

    /**
     * JsonNullable 처리는 @AfterMapping에서 일괄 적용
     */
    @AfterMapping
    protected fun after(@MappingTarget experience: Experience, dto: ExperienceUpdateDto) {
        dto.title.ifPresent { it?.let { v -> experience.title = v } }
        dto.details.ifPresent { experience.details = it }
    }
}


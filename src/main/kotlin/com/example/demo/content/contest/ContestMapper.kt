package com.example.demo.content.contest

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
        dto.subtitle.ifPresent { contest.subtitle = it }
        dto.targetParticipants.ifPresent { contest.targetParticipants = it }
        dto.extraInfo.ifPresent { contest.extraInfo = it }
        dto.description.ifPresent { contest.description = it }
        dto.websiteUrl.ifPresent { contest.websiteUrl = it }
        dto.contact.ifPresent { contest.contact = it }
    }
}

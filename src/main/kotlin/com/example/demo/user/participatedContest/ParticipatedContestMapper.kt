package com.example.demo.user.participatedContest

import com.example.demo.user.ifPresent
import org.mapstruct.*
import org.springframework.stereotype.Component

@Mapper(componentModel = "spring")
abstract class ParticipatedContestMapper {

    /**
     * 부분 업데이트: DTO에서 isPresent인 필드만 엔티티에 반영
     */
    @BeanMapping(ignoreByDefault = true)
    abstract fun partialUpdate(@MappingTarget participatedContest: ParticipatedContest, dto: ParticipatedContestUpdateDto)

    /**
     * JsonNullable 처리는 @AfterMapping에서 일괄 적용
     */
    @AfterMapping
    protected fun after(@MappingTarget participatedContest: ParticipatedContest, dto: ParticipatedContestUpdateDto) {
        dto.title.ifPresent { it?.let { v -> participatedContest.title = v } }
        dto.details.ifPresent { participatedContest.details = it }
    }
}

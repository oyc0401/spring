package com.example.demo.user.recommend

import com.example.demo.user.ifPresent
import org.mapstruct.*
import org.springframework.stereotype.Component

@Mapper(componentModel = "spring")
abstract class RecommendMapper {

    /**
     * 부분 업데이트: DTO에서 isPresent인 필드만 엔티티에 반영
     */
    @BeanMapping(ignoreByDefault = true)
    abstract fun partialUpdate(@MappingTarget recommend: Recommend, dto: RecommendUpdateDto)

    /**
     * JsonNullable 처리는 @AfterMapping에서 일괄 적용
     */
    @AfterMapping
    protected fun after(@MappingTarget recommend: Recommend, dto: RecommendUpdateDto) {
        dto.view.ifPresent { recommend.view = it }
    }
}

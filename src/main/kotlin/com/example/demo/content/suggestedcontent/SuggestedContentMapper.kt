package com.example.demo.content.suggestedcontent

import org.openapitools.jackson.nullable.JsonNullable
import org.mapstruct.*
import org.springframework.stereotype.Component

inline fun <T> JsonNullable<T>.ifPresent(apply: (T?) -> Unit) {
    if (isPresent) apply(this.orElse(null))
}

@Mapper(componentModel = "spring")
abstract class SuggestedContentMapper {

    @BeanMapping(ignoreByDefault = true)
    abstract fun partialUpdate(@MappingTarget suggestedContent: SuggestedContent, dto: SuggestedContentUpdateDto)

    @AfterMapping
    protected fun after(@MappingTarget suggestedContent: SuggestedContent, dto: SuggestedContentUpdateDto) {
        dto.userId.ifPresent { it?.let { v -> suggestedContent.userId = v } }
        dto.contentId.ifPresent { it?.let { v -> suggestedContent.contentId = v } }
        dto.type.ifPresent { it?.let { v -> suggestedContent.type = v } }
    }
}
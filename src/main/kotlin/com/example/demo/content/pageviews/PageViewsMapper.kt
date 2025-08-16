package com.example.demo.content.pageviews

import org.openapitools.jackson.nullable.JsonNullable
import org.mapstruct.*
import org.springframework.stereotype.Component

inline fun <T> JsonNullable<T>.ifPresent(apply: (T?) -> Unit) {
    if (isPresent) apply(this.orElse(null))
}

@Mapper(componentModel = "spring")
abstract class PageViewsMapper {

    @BeanMapping(ignoreByDefault = true)
    abstract fun partialUpdate(@MappingTarget pageViews: PageViews, dto: PageViewsUpdateDto)

    @AfterMapping
    protected fun after(@MappingTarget pageViews: PageViews, dto: PageViewsUpdateDto) {
        dto.contentId.ifPresent { it?.let { v -> pageViews.contentId = v } }
        dto.userId.ifPresent { it?.let { v -> pageViews.userId = v } }
    }
}
package com.example.demo.content.tagType

import org.mapstruct.*

@Mapper(componentModel = "spring")
abstract class TagMapper {

    @BeanMapping(ignoreByDefault = true)
    abstract fun partialUpdate(@MappingTarget tag: Tag, dto: TagUpdateDto)

    @AfterMapping
    protected fun after(@MappingTarget tag: Tag, dto: TagUpdateDto) {
        dto.tagName.ifPresent { it?.let { v -> tag.tagName = v } }
    }
}
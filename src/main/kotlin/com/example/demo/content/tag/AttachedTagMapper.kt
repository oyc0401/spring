package com.example.demo.content.tag

import org.mapstruct.*

@Mapper(componentModel = "spring")
abstract class AttachedTagMapper {

    @BeanMapping(ignoreByDefault = true)
    abstract fun partialUpdate(@MappingTarget attachedTag: AttachedTag, dto: AttachedTagUpdateDto)

    @AfterMapping
    protected fun after(@MappingTarget attachedTag: AttachedTag, dto: AttachedTagUpdateDto) {
        dto.name.ifPresent { attachedTag.name = it }
    }
}

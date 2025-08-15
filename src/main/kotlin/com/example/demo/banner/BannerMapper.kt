package com.example.demo.banner

import com.example.demo.user.ifPresent
import org.mapstruct.*

@Mapper(componentModel = "spring")
abstract class BannerMapper {

    @BeanMapping(ignoreByDefault = true)
    abstract fun partialUpdate(@MappingTarget banner: Banner, dto: BannerUpdateDto)

    @AfterMapping
    protected fun after(@MappingTarget banner: Banner, dto: BannerUpdateDto) {
        dto.title.ifPresent { it?.let { v -> banner.title = v } }
        dto.imageUrl.ifPresent { it?.let { v -> banner.imageUrl = v } }
        dto.linkUrl.ifPresent { banner.linkUrl = it }
        dto.description.ifPresent { banner.description = it }
        dto.isActive.ifPresent { banner.isActive = it }
        dto.startDate.ifPresent { banner.startDate = it }
        dto.endDate.ifPresent { banner.endDate = it }
        dto.priority.ifPresent { banner.priority = it }
    }
}
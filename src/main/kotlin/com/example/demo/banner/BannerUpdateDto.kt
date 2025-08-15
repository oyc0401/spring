package com.example.demo.banner

import org.openapitools.jackson.nullable.JsonNullable
import java.time.LocalDate

data class BannerUpdateDto(
    val title: JsonNullable<String> = JsonNullable.undefined(),
    val imageUrl: JsonNullable<String> = JsonNullable.undefined(),
    val linkUrl: JsonNullable<String> = JsonNullable.undefined(),
    val description: JsonNullable<String> = JsonNullable.undefined(),
    val isActive: JsonNullable<Boolean> = JsonNullable.undefined(),
    val startDate: JsonNullable<LocalDate> = JsonNullable.undefined(),
    val endDate: JsonNullable<LocalDate> = JsonNullable.undefined(),
    val priority: JsonNullable<Int> = JsonNullable.undefined()
)
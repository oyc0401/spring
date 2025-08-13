package com.example.demo.content.contest

import org.openapitools.jackson.nullable.JsonNullable
import java.time.LocalDateTime

data class ContestUpdateDto(
    val bannerUrl: JsonNullable<String?> = JsonNullable.undefined(),
    val title: JsonNullable<String> = JsonNullable.undefined(),
    val subtitle: JsonNullable<String?> = JsonNullable.undefined(),
    val writer: JsonNullable<String?> = JsonNullable.undefined(),
    val companyType: JsonNullable<String?> = JsonNullable.undefined(),
    val targetParticipants: JsonNullable<String?> = JsonNullable.undefined(),
    val startTime: JsonNullable<LocalDateTime?> = JsonNullable.undefined(),
    val endTime: JsonNullable<LocalDateTime?> = JsonNullable.undefined(),
    val extraInfo: JsonNullable<String?> = JsonNullable.undefined(),
    val description: JsonNullable<String?> = JsonNullable.undefined(),
    val websiteUrl: JsonNullable<String?> = JsonNullable.undefined(),
    val contact: JsonNullable<String?> = JsonNullable.undefined()
)

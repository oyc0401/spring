package com.example.demo.content.contest

import org.openapitools.jackson.nullable.JsonNullable
import java.time.LocalDateTime

data class ContestUpdateDto(


    val subtitle: JsonNullable<String?> = JsonNullable.undefined(),

    val targetParticipants: JsonNullable<String?> = JsonNullable.undefined(),

    val extraInfo: JsonNullable<String?> = JsonNullable.undefined(),
    val description: JsonNullable<String?> = JsonNullable.undefined(),
    val websiteUrl: JsonNullable<String?> = JsonNullable.undefined(),
    val contact: JsonNullable<String?> = JsonNullable.undefined()
)

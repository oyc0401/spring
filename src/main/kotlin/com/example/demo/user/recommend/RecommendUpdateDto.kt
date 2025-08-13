package com.example.demo.user.recommend

import org.openapitools.jackson.nullable.JsonNullable

data class RecommendUpdateDto(
    val title: JsonNullable<String> = JsonNullable.undefined(),
    val details: JsonNullable<String?> = JsonNullable.undefined()
)

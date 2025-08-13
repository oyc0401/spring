package com.example.demo.user.recommend

import org.openapitools.jackson.nullable.JsonNullable

data class RecommendUpdateDto(
    val view: JsonNullable<Boolean> = JsonNullable.undefined()
)

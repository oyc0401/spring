package com.example.demo.user.contest

import org.openapitools.jackson.nullable.JsonNullable

data class ContestUpdateDto(
    val title: JsonNullable<String> = JsonNullable.undefined(),
    val details: JsonNullable<String?> = JsonNullable.undefined()
)

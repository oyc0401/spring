package com.example.demo.user.activity

import org.openapitools.jackson.nullable.JsonNullable

data class ActivityUpdateDto(
    val title: JsonNullable<String> = JsonNullable.undefined(),
    val details: JsonNullable<String?> = JsonNullable.undefined()
)

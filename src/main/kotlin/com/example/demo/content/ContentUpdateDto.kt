package com.example.demo.content

import org.openapitools.jackson.nullable.JsonNullable

data class ContentUpdateDto(
    val viewCount: JsonNullable<Int> = JsonNullable.undefined(),
    val bookmarkCount: JsonNullable<Int> = JsonNullable.undefined()
)

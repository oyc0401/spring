package com.example.demo.content

import org.openapitools.jackson.nullable.JsonNullable

data class ContentUpdateDto(
    val viewCount: JsonNullable<Int> = JsonNullable.undefined(),
    val scrapCount: JsonNullable<Int> = JsonNullable.undefined()
)

package com.example.demo.content.suggestedcontent

import org.openapitools.jackson.nullable.JsonNullable

data class SuggestedContentUpdateDto(
    val userId: JsonNullable<Int> = JsonNullable.undefined(),
    val contentId: JsonNullable<Int> = JsonNullable.undefined(),
    val type: JsonNullable<String> = JsonNullable.undefined()
)
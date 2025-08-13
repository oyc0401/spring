package com.example.demo.user.bookmarkedContents

import org.openapitools.jackson.nullable.JsonNullable

data class BookmarkedContentUpdateDto(
    val title: JsonNullable<String> = JsonNullable.undefined(),
    val details: JsonNullable<String?> = JsonNullable.undefined()
)

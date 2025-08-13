package com.example.demo.user.recentSearch

import org.openapitools.jackson.nullable.JsonNullable

data class RecentSearchUpdateDto(
    val title: JsonNullable<String> = JsonNullable.undefined(),
    val details: JsonNullable<String?> = JsonNullable.undefined()
)

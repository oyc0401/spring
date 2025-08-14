package com.example.demo.user.recentSearch

import org.openapitools.jackson.nullable.JsonNullable

data class RecentSearchUpdateDto(
    val text: JsonNullable<String> = JsonNullable.undefined(),
)

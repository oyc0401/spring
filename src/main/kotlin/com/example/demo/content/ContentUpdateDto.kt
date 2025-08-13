package com.example.demo.content

import org.openapitools.jackson.nullable.JsonNullable

data class ContentUpdateDto(
    val title: JsonNullable<String> = JsonNullable.undefined(),
    val bannerUrl: JsonNullable<String> = JsonNullable.undefined(),
    val writer: JsonNullable<String> = JsonNullable.undefined(),
    val companyType: JsonNullable<String> = JsonNullable.undefined(),
    val startTime: JsonNullable<String> = JsonNullable.undefined(),
    val endTime: JsonNullable<String> = JsonNullable.undefined(),
    val viewCount: JsonNullable<Int> = JsonNullable.undefined(),
    val bookmarkCount: JsonNullable<Int> = JsonNullable.undefined()
)

package com.example.demo.content.pageviews

import org.openapitools.jackson.nullable.JsonNullable

data class PageViewsUpdateDto(
    val contentId: JsonNullable<Int> = JsonNullable.undefined(),
    val userId: JsonNullable<Int> = JsonNullable.undefined()
)
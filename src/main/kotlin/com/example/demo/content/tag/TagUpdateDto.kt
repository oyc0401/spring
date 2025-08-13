package com.example.demo.content.tag

import org.openapitools.jackson.nullable.JsonNullable

data class TagUpdateDto(
    val tagName: JsonNullable<String> = JsonNullable.undefined()
)

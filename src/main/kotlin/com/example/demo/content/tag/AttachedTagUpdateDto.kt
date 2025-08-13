package com.example.demo.content.tag

import org.openapitools.jackson.nullable.JsonNullable

data class AttachedTagUpdateDto(
    val name: JsonNullable<String> = JsonNullable.undefined()
)

package com.example.demo.user.skill

import org.openapitools.jackson.nullable.JsonNullable

data class SkillUpdateDto(
    val title: JsonNullable<String> = JsonNullable.undefined(),
    val details: JsonNullable<String?> = JsonNullable.undefined()
)

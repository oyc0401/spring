
package com.example.demo.user.portfolio

import org.openapitools.jackson.nullable.JsonNullable

data class ExperienceUpdateDto(
    val title: JsonNullable<String> = JsonNullable.undefined(),
    val details: JsonNullable<String?> = JsonNullable.undefined()
)

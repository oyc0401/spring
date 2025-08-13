
package com.example.demo.user.experience

import org.openapitools.jackson.nullable.JsonNullable

data class ExperienceUpdateDto(
    val title: JsonNullable<String> = JsonNullable.undefined(),
    val details: JsonNullable<String?> = JsonNullable.undefined()
)

package com.example.demo.user

import org.openapitools.jackson.nullable.JsonNullable

data class UserUpdateDto(
    val name: JsonNullable<String> = JsonNullable.undefined(),
    val email: JsonNullable<String?> = JsonNullable.undefined(),
    val school: JsonNullable<String?> = JsonNullable.undefined(),
    val schoolStatus: JsonNullable<String?> = JsonNullable.undefined(),
    val grade: JsonNullable<String?> = JsonNullable.undefined(),
    val isGradePublic: JsonNullable<Boolean> = JsonNullable.undefined(),
    val residenceCity: JsonNullable<String?> = JsonNullable.undefined(),
    val residenceCountry: JsonNullable<String?> = JsonNullable.undefined(),
    val isResidencePublic: JsonNullable<Boolean> = JsonNullable.undefined(),
    val bio: JsonNullable<String?> = JsonNullable.undefined(),
    val vision: JsonNullable<String?> = JsonNullable.undefined()
)
package com.example.demo.user.participatedContest

import org.openapitools.jackson.nullable.JsonNullable

data class ParticipatedContestUpdateDto(
    val title: JsonNullable<String> = JsonNullable.undefined(),
    val details: JsonNullable<String?> = JsonNullable.undefined()
)

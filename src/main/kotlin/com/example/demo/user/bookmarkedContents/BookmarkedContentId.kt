package com.example.demo.user.bookmarkedContents

import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class BookmarkedContentId(
    val userId: Int = 0,
    val contentId: Int = 0
) : Serializable

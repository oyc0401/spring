package com.example.demo.user.recommend

import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class RecommendId(
    val userId: Int = 0,
    val contentId: Int = 0
) : Serializable

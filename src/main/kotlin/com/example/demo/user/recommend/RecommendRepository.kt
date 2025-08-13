package com.example.demo.user.recommend

import org.springframework.data.jpa.repository.JpaRepository

interface RecommendRepository : JpaRepository<Recommend, RecommendId> {

    fun findAllByIdUserId(userId: Int): List<Recommend>
}

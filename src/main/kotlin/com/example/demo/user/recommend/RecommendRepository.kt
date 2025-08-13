package com.example.demo.user.recommend

import org.springframework.data.jpa.repository.JpaRepository

interface RecommendRepository : JpaRepository<Recommend, Int> {

    fun findByUserId(userId: Int): List<Recommend>
}

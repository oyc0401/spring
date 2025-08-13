package com.example.demo.user.recommend

import org.springframework.data.jpa.repository.JpaRepository

interface RecommendRepository : JpaRepository<Recommend, RecommendId> {

    fun findByUserId(userId: Int): List<Recommend>
    
    fun findByUserIdAndContentId(userId: Int, contentId: Int): Recommend?
    
    fun deleteByUserIdAndContentId(userId: Int, contentId: Int)
}

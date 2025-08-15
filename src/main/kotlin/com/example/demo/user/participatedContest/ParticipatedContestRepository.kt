package com.example.demo.user.participatedContest

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ParticipatedContestRepository : JpaRepository<ParticipatedContest, Int> {

    fun findByUserIdOrderByPriorityDescIdDesc(userId: Int): List<ParticipatedContest>
    
    @Query("SELECT COALESCE(MAX(p.priority), 0) FROM ParticipatedContest p WHERE p.userId = :userId")
    fun findMaxPriorityByUserId(userId: Int): Int
}

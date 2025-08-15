package com.example.demo.user.activity

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ActivityRepository : JpaRepository<Activity, Int> {

    fun findByUserIdOrderByPriorityDescIdDesc(userId: Int): List<Activity>
    
    @Query("SELECT COALESCE(MAX(a.priority), 0) FROM Activity a WHERE a.userId = :userId")
    fun findMaxPriorityByUserId(userId: Int): Int
}

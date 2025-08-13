package com.example.demo.user.activity

import org.springframework.data.jpa.repository.JpaRepository

interface ActivityRepository : JpaRepository<Activity, Int> {

    fun findByUserId(userId: Int): List<Activity>
}

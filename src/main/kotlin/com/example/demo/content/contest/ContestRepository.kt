package com.example.demo.content.contest

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ContestRepository : JpaRepository<Contest, Int> {

//    @Query("SELECT c FROM Contest c WHERE c.startime <= :now AND c.endTime >= :now")
//    fun findActiveContests(@Param("now") now: java.time.LocalDateTime): List<Contest>

//    @Query("SELECT c FROM Contest c WHERE c.title LIKE %:keyword% OR c.description LIKE %:keyword% OR c.subtitle LIKE %:keyword%")
//    fun searchContests(@Param("keyword") keyword: String): List<Contest>

//    fun findByWriter(writer: String): List<Contest>

//    fun findByCompanyType(companyType: String): List<Contest>

//    @Query("SELECT c FROM Contest c WHERE c.startTime > :now")
//    fun findUpcomingContests(@Param("now") now: java.time.LocalDateTime): List<Contest>

//    @Query("SELECT c FROM Contest c WHERE c.endTime < :now")
//    fun findEndedContests(@Param("now") now: java.time.LocalDateTime): List<Contest>
}

package com.example.demo.banner

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDate

interface BannerRepository : JpaRepository<Banner, Int> {
    
    @Query("SELECT b FROM Banner b WHERE b.isActive = true ORDER BY b.priority DESC, b.id DESC")
    fun findActiveByPriorityDesc(): List<Banner>
    
    @Query("SELECT b FROM Banner b WHERE b.isActive = true AND (b.startDate IS NULL OR b.startDate <= :currentDate) AND (b.endDate IS NULL OR b.endDate >= :currentDate) ORDER BY b.priority DESC, b.id DESC")
    fun findActiveAndCurrentByPriorityDesc(currentDate: LocalDate = LocalDate.now()): List<Banner>
    
    fun findByIsActiveTrueOrderByPriorityDescIdDesc(): List<Banner>
}
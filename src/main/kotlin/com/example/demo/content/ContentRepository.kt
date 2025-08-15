package com.example.demo.content

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ContentRepository : JpaRepository<Content, Int> {
    
    fun findByIsDeletedFalseAndIsActiveTrueOrderByCreatedAtDesc(): List<Content>
    
    fun findByIsDeletedFalseAndIsActiveTrueAndIsFeaturedTrueOrderByCreatedAtDesc(): List<Content>
    
    fun findByIsDeletedFalseAndIsActiveTrueAndTypeOrderByCreatedAtDesc(type: String): List<Content>
    
    @Query("SELECT c FROM Content c WHERE c.isDeleted = false AND c.isActive = true AND c.id = :id")
    fun findActiveById(id: Int): Content?
}

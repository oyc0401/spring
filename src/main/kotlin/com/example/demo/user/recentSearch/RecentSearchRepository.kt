package com.example.demo.user.recentSearch

import org.springframework.data.jpa.repository.JpaRepository

interface RecentSearchRepository : JpaRepository<RecentSearch, Int> {

    fun findByUserId(userId: Int): List<RecentSearch>
}

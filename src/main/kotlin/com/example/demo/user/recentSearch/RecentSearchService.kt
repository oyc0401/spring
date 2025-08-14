package com.example.demo.user.recentSearch

import org.springframework.stereotype.Service
import java.util.NoSuchElementException

@Service
class RecentSearchService(
    private val recentSearchRepository: RecentSearchRepository,
    private val mapper: RecentSearchMapper
) {
    data class RecentSearchRequest(
        val text: String,
    )

    fun addRecentSearch(userId: Int, request: RecentSearchRequest): RecentSearch {
        val recentSearch = RecentSearch(
            userId = userId,
            text = request.text,
        )

        return recentSearchRepository.save(recentSearch)
    }

    fun getRecentSearches(userId: Int): List<RecentSearch> {
        return recentSearchRepository.findByUserId(userId)
    }

    fun getRecentSearch(userId: Int, recentSearchId: Int): RecentSearch {
        val recentSearch = recentSearchRepository.findById(recentSearchId)
            .orElseThrow { NoSuchElementException("RecentSearch not found") }

        if (recentSearch.userId != userId) {
            throw IllegalArgumentException("Access denied: RecentSearch does not belong to user")
        }

        return recentSearch
    }

    fun updateRecentSearch(userId: Int, recentSearchId: Int, dto: RecentSearchUpdateDto): RecentSearch {
        val recentSearch = recentSearchRepository.findById(recentSearchId)
            .orElseThrow { NoSuchElementException("RecentSearch not found") }

        if (recentSearch.userId != userId) {
            throw IllegalArgumentException("Access denied: RecentSearch does not belong to user")
        }

        mapper.partialUpdate(recentSearch, dto)
        return recentSearch // JPA dirty checking으로 자동 저장
    }

    fun deleteRecentSearch(userId: Int, recentSearchId: Int) {
        val recentSearch = recentSearchRepository.findById(recentSearchId)
            .orElseThrow { NoSuchElementException("RecentSearch not found") }

        if (recentSearch.userId != userId) {
            throw IllegalArgumentException("Access denied: RecentSearch does not belong to user")
        }

        recentSearchRepository.delete(recentSearch)
    }
}

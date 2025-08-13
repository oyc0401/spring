package com.example.demo.user.recommend

import org.springframework.stereotype.Service
import java.util.NoSuchElementException

@Service
class RecommendService(
    private val recommendRepository: RecommendRepository,
    private val mapper: RecommendMapper
) {
    data class RecommendRequest(
        val title: String,
        val details: String,
    )

    fun addRecommend(userId: Int, request: RecommendRequest): Recommend {
        val recommend = Recommend(
            userId = userId,
            title = request.title,
            details = request.details
        )

        return recommendRepository.save(recommend)
    }

    fun getRecommends(userId: Int): List<Recommend> {
        return recommendRepository.findByUserId(userId)
    }

    fun getRecommend(userId: Int, recommendId: Int): Recommend {
        val recommend = recommendRepository.findById(recommendId)
            .orElseThrow { NoSuchElementException("Recommend not found") }

        if (recommend.userId != userId) {
            throw IllegalArgumentException("Access denied: Recommend does not belong to user")
        }

        return recommend
    }

    fun updateRecommend(userId: Int, recommendId: Int, dto: RecommendUpdateDto): Recommend {
        val recommend = recommendRepository.findById(recommendId)
            .orElseThrow { NoSuchElementException("Recommend not found") }

        if (recommend.userId != userId) {
            throw IllegalArgumentException("Access denied: Recommend does not belong to user")
        }

        mapper.partialUpdate(recommend, dto)
        return recommend // JPA dirty checking으로 자동 저장
    }

    fun deleteRecommend(userId: Int, recommendId: Int) {
        val recommend = recommendRepository.findById(recommendId)
            .orElseThrow { NoSuchElementException("Recommend not found") }

        if (recommend.userId != userId) {
            throw IllegalArgumentException("Access denied: Recommend does not belong to user")
        }

        recommendRepository.delete(recommend)
    }
}

package com.example.demo.user.recommend

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.NoSuchElementException

@Service
class RecommendService(
    private val recommendRepository: RecommendRepository,
    private val mapper: RecommendMapper
) {
    data class RecommendRequest(
        val contentId: Int,
        val view: Boolean = false
    )

    fun addRecommend(userId: Int, request: RecommendRequest): Recommend {
        val recommend = Recommend(
            id = RecommendId(userId = userId, contentId = request.contentId),
            view = request.view
        )

        return recommendRepository.save(recommend)
    }

    fun getRecommends(userId: Int): List<Recommend> {
        return recommendRepository.findAllByIdUserId(userId)
    }

    fun getRecommend(userId: Int, contentId: Int): Recommend {
        return recommendRepository.findById(RecommendId(userId = userId, contentId = contentId))
            .orElseThrow { NoSuchElementException("Recommend not found") }
    }

    fun updateRecommend(userId: Int, contentId: Int, dto: RecommendUpdateDto): Recommend {
        val recommend = recommendRepository.findById(RecommendId(userId = userId, contentId = contentId))
            .orElseThrow { NoSuchElementException("Recommend not found") }

        mapper.partialUpdate(recommend, dto)
        return recommend // JPA dirty checking으로 자동 저장
    }

    fun deleteRecommend(userId: Int, contentId: Int) {
        val recommend = recommendRepository.findById(RecommendId(userId = userId, contentId = contentId))
            .orElseThrow { NoSuchElementException("Recommend not found") }

        recommendRepository.deleteById(RecommendId(userId = userId, contentId = contentId))
    }
}

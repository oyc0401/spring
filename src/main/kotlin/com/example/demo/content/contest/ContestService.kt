package com.example.demo.content.contest

import com.example.demo.content.Content
import com.example.demo.content.ContentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.NoSuchElementException

@Service
class ContestService(
    private val contestRepository: ContestRepository,
    private val contentRepository: ContentRepository,
    private val mapper: ContestMapper
) {
    data class ContestRequest(
        val subtitle: String? = null,
        val targetParticipants: String? = null,
        val extraInfo: String? = null,
        val description: String? = null,
        val websiteUrl: String? = null,
        val contact: String? = null,
    )

    fun getAllContests(): List<Contest> {
        return contestRepository.findAll()
    }

    fun getContest(contestId: Int): Contest {
        return contestRepository.findById(contestId)
            .orElseThrow { NoSuchElementException("Contest not found") }
    }

    @Transactional
    fun createContest(request: ContestRequest): Contest {
        // Create Content first
        val content = Content()
        val savedContent = contentRepository.save(content)

        // Create Contest with Content reference
        val contest = Contest(
            content = savedContent,
            subtitle = request.subtitle,
            targetParticipants = request.targetParticipants,
            extraInfo = request.extraInfo,
            description = request.description,
            websiteUrl = request.websiteUrl,
            contact = request.contact,

        )

        return contestRepository.save(contest)
    }

    @Transactional
    fun updateContest(contestId: Int, dto: ContestUpdateDto): Contest {
        val contest = contestRepository.findById(contestId)
            .orElseThrow { NoSuchElementException("Contest not found") }

        // Contest 업데이트
        mapper.partialUpdate(contest, dto)
        
        // Content의 updatedAt 업데이트
        contest.content.updatedAt = LocalDateTime.now()
        
        return contest // JPA dirty checking으로 자동 저장
    }
}

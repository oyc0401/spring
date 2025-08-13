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
        val title: String,
        val subtitle: String? = null,
        val writer: String? = null,
        val companyType: String? = null,
        val targetParticipants: String? = null,
        val startTime: LocalDateTime? = null,
        val endTime: LocalDateTime? = null,
        val extraInfo: String? = null,
        val description: String? = null,
        val websiteUrl: String? = null,
        val contact: String? = null,
        val bannerUrl: String? = null
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
            contentId = savedContent.id,
            content = savedContent,
            title = request.title,
            subtitle = request.subtitle,
            writer = request.writer,
            companyType = request.companyType,
            targetParticipants = request.targetParticipants,
            startTime = request.startTime,
            endTime = request.endTime,
            extraInfo = request.extraInfo,
            description = request.description,
            websiteUrl = request.websiteUrl,
            contact = request.contact,
            bannerUrl = request.bannerUrl
        )

        return contestRepository.save(contest)
    }

    @Transactional
    fun updateContest(contestId: Int, dto: ContestUpdateDto): Contest {
        val contest = contestRepository.findById(contestId)
            .orElseThrow { NoSuchElementException("Contest not found") }

        mapper.partialUpdate(contest, dto)
        return contest // JPA dirty checking으로 자동 저장
    }
}

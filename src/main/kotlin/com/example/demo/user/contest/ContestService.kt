package com.example.demo.user.contest

import org.springframework.stereotype.Service
import java.util.NoSuchElementException

@Service
class ContestService(
    private val contestRepository: ContestRepository,
    private val mapper: ContestMapper
) {
    data class ContestRequest(
        val title: String,
        val details: String,
    )

    fun addContest(userId: Int, request: ContestRequest): Contest {
        val contest = Contest(
            userId = userId,
            title = request.title,
            details = request.details
        )

        return contestRepository.save(contest)
    }

    fun getContests(userId: Int): List<Contest> {
        return contestRepository.findByUserId(userId)
    }

    fun getContest(userId: Int, contestId: Int): Contest {
        val contest = contestRepository.findById(contestId)
            .orElseThrow { NoSuchElementException("Contest not found") }

        if (contest.userId != userId) {
            throw IllegalArgumentException("Access denied: Contest does not belong to user")
        }

        return contest
    }

    fun updateContest(userId: Int, contestId: Int, dto: ContestUpdateDto): Contest {
        val contest = contestRepository.findById(contestId)
            .orElseThrow { NoSuchElementException("Contest not found") }

        if (contest.userId != userId) {
            throw IllegalArgumentException("Access denied: Contest does not belong to user")
        }

        mapper.partialUpdate(contest, dto)
        return contest // JPA dirty checking으로 자동 저장
    }

    fun deleteContest(userId: Int, contestId: Int) {
        val contest = contestRepository.findById(contestId)
            .orElseThrow { NoSuchElementException("Contest not found") }

        if (contest.userId != userId) {
            throw IllegalArgumentException("Access denied: Contest does not belong to user")
        }

        contestRepository.delete(contest)
    }
}

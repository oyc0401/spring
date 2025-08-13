package com.example.demo.user.participatedContest

import org.springframework.stereotype.Service
import java.util.NoSuchElementException

@Service
class ParticipatedContestService(
    private val participatedContestRepository: ParticipatedContestRepository,
    private val mapper: ParticipatedContestMapper
) {
    data class ParticipatedContestRequest(
        val title: String,
        val details: String,
    )

    fun addParticipatedContest(userId: Int, request: ParticipatedContestRequest): ParticipatedContest {
        val participatedContest = ParticipatedContest(
            userId = userId,
            title = request.title,
            details = request.details
        )

        return participatedContestRepository.save(participatedContest)
    }

    fun getParticipatedContests(userId: Int): List<ParticipatedContest> {
        return participatedContestRepository.findByUserId(userId)
    }

    fun getParticipatedContest(userId: Int, participatedContestId: Int): ParticipatedContest {
        val participatedContest = participatedContestRepository.findById(participatedContestId)
            .orElseThrow { NoSuchElementException("ParticipatedContest not found") }

        if (participatedContest.userId != userId) {
            throw IllegalArgumentException("Access denied: ParticipatedContest does not belong to user")
        }

        return participatedContest
    }

    fun updateParticipatedContest(userId: Int, participatedContestId: Int, dto: ParticipatedContestUpdateDto): ParticipatedContest {
        val participatedContest = participatedContestRepository.findById(participatedContestId)
            .orElseThrow { NoSuchElementException("ParticipatedContest not found") }

        if (participatedContest.userId != userId) {
            throw IllegalArgumentException("Access denied: ParticipatedContest does not belong to user")
        }

        mapper.partialUpdate(participatedContest, dto)
        return participatedContest // JPA dirty checking으로 자동 저장
    }

    fun deleteParticipatedContest(userId: Int, participatedContestId: Int) {
        val participatedContest = participatedContestRepository.findById(participatedContestId)
            .orElseThrow { NoSuchElementException("ParticipatedContest not found") }

        if (participatedContest.userId != userId) {
            throw IllegalArgumentException("Access denied: ParticipatedContest does not belong to user")
        }

        participatedContestRepository.delete(participatedContest)
    }
}

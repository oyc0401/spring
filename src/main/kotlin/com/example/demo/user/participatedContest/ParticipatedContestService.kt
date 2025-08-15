package com.example.demo.user.participatedContest

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
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

    data class SwapPriorityRequest(
        val participatedContestId1: Int,
        val participatedContestId2: Int
    )

    fun addParticipatedContest(userId: Int, request: ParticipatedContestRequest): ParticipatedContest {
        val maxPriority = participatedContestRepository.findMaxPriorityByUserId(userId)
        val participatedContest = ParticipatedContest(
            userId = userId,
            title = request.title,
            details = request.details,
            priority = maxPriority + 1
        )

        return participatedContestRepository.save(participatedContest)
    }

    fun getParticipatedContests(userId: Int): List<ParticipatedContest> {
        return participatedContestRepository.findByUserIdOrderByPriorityDescIdDesc(userId)
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

    @Transactional
    fun swapParticipatedContestPriority(userId: Int, participatedContestId1: Int, participatedContestId2: Int) {
        val participatedContest1 = participatedContestRepository.findById(participatedContestId1)
            .orElseThrow { NoSuchElementException("ParticipatedContest with id $participatedContestId1 not found") }
        val participatedContest2 = participatedContestRepository.findById(participatedContestId2)
            .orElseThrow { NoSuchElementException("ParticipatedContest with id $participatedContestId2 not found") }

        if (participatedContest1.userId != userId || participatedContest2.userId != userId) {
            throw IllegalArgumentException("Access denied: ParticipatedContests do not belong to user")
        }

        val tempPriority = participatedContest1.priority
        participatedContest1.priority = participatedContest2.priority
        participatedContest2.priority = tempPriority
    }
}

package com.example.demo.user.participatedContest

import org.springframework.data.jpa.repository.JpaRepository

interface ParticipatedContestRepository : JpaRepository<ParticipatedContest, Int> {

    fun findByUserId(userId: Int): List<ParticipatedContest>
}

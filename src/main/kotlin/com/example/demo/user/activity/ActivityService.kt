package com.example.demo.user.activity

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.NoSuchElementException

@Service
class ActivityService(
    private val activityRepository: ActivityRepository,
    private val mapper: ActivityMapper
) {
    data class ActivityRequest(
        val title: String,
        val details: String,
    )

    data class SwapPriorityRequest(
        val activityId1: Int,
        val activityId2: Int
    )

    fun addActivity(userId: Int, request: ActivityRequest): Activity {
        val maxPriority = activityRepository.findMaxPriorityByUserId(userId)
        val activity = Activity(
            userId = userId,
            title = request.title,
            details = request.details,
            priority = maxPriority + 1
        )

        return activityRepository.save(activity)
    }

    fun getActivities(userId: Int): List<Activity> {
        return activityRepository.findByUserIdOrderByPriorityDescIdDesc(userId)
    }

    fun getActivity(userId: Int, activityId: Int): Activity {
        val activity = activityRepository.findById(activityId)
            .orElseThrow { NoSuchElementException("Activity not found") }

        if (activity.userId != userId) {
            throw IllegalArgumentException("Access denied: Activity does not belong to user")
        }

        return activity
    }

    fun updateActivity(userId: Int, activityId: Int, dto: ActivityUpdateDto): Activity {
        val activity = activityRepository.findById(activityId)
            .orElseThrow { NoSuchElementException("Activity not found") }

        if (activity.userId != userId) {
            throw IllegalArgumentException("Access denied: Activity does not belong to user")
        }

        mapper.partialUpdate(activity, dto)
        return activity // JPA dirty checking으로 자동 저장
    }

    fun deleteActivity(userId: Int, activityId: Int) {
        val activity = activityRepository.findById(activityId)
            .orElseThrow { NoSuchElementException("Activity not found") }

        if (activity.userId != userId) {
            throw IllegalArgumentException("Access denied: Activity does not belong to user")
        }

        activityRepository.delete(activity)
    }

    @Transactional
    fun swapActivityPriority(userId: Int, activityId1: Int, activityId2: Int) {
        val activity1 = activityRepository.findById(activityId1)
            .orElseThrow { NoSuchElementException("Activity with id $activityId1 not found") }
        val activity2 = activityRepository.findById(activityId2)
            .orElseThrow { NoSuchElementException("Activity with id $activityId2 not found") }

        if (activity1.userId != userId || activity2.userId != userId) {
            throw IllegalArgumentException("Access denied: Activities do not belong to user")
        }

        val tempPriority = activity1.priority
        activity1.priority = activity2.priority
        activity2.priority = tempPriority
    }
}

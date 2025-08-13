package com.example.demo.user.activity

import org.springframework.stereotype.Service
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

    fun addActivity(userId: Int, request: ActivityRequest): Activity {
        val activity = Activity(
            userId = userId,
            title = request.title,
            details = request.details
        )

        return activityRepository.save(activity)
    }

    fun getActivities(userId: Int): List<Activity> {
        return activityRepository.findByUserId(userId)
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
}

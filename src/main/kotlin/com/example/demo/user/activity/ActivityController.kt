package com.example.demo.user.activity

import com.example.demo.security.UserPrincipal
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/users/activity")
class ActivityController(
    private val activityService: ActivityService
) {

    @GetMapping("/")
    fun getActivities(
        @AuthenticationPrincipal user: UserPrincipal
    ): List<Activity> {
        return activityService.getActivities(user.userId)
    }

    @GetMapping("/{id}")
    fun getActivity(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable id: Int
    ): Activity {
        return activityService.getActivity(user.userId, id)
    }

    @PostMapping("/add")
    fun addActivity(
        @AuthenticationPrincipal user: UserPrincipal,
        @RequestBody request: ActivityService.ActivityRequest
    ): Activity {
        return activityService.addActivity(user.userId, request)
    }

    @PostMapping("/{id}/update")
    fun updateActivity(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable id: Int,
        @RequestBody dto: ActivityUpdateDto
    ): Activity {
        return activityService.updateActivity(user.userId, id, dto)
    }

    @DeleteMapping("/{id}")
    fun deleteActivity(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable id: Int
    ): ResponseEntity<Unit> {
        activityService.deleteActivity(user.userId, id)
        return ResponseEntity.ok().build()
    }
}

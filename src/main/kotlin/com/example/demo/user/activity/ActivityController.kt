package com.example.demo.user.activity

import com.example.demo.security.UserPrincipal
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@SecurityRequirement(name = "bearerAuth")
@RestController
@Tag(name = "user.activity-controller", description = "보유 대외활동 API")
@RequestMapping("/user/activity")
class ActivityController(
    private val activityService: ActivityService
) {

    @Operation(summary = "내 대외활동 목록 조회", description = "현재 사용자의 보유 대외활동 목록을 조회합니다")
    @GetMapping("/")
    fun getActivities(
        @AuthenticationPrincipal user: UserPrincipal
    ): List<Activity> {
        return activityService.getActivities(user.userId)
    }

    @Operation(summary = "대외활동 상세 조회", description = "특정 대외활동의 상세 정보를 조회합니다")
    @GetMapping("/{id}")
    fun getActivity(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable id: Int
    ): Activity {
        return activityService.getActivity(user.userId, id)
    }

    @Operation(summary = "대외활동 추가", description = "새로운 보유 대외활동을 추가합니다")
    @PostMapping("/add")
    fun addActivity(
        @AuthenticationPrincipal user: UserPrincipal,
        @RequestBody request: ActivityService.ActivityRequest
    ): Activity {
        return activityService.addActivity(user.userId, request)
    }

    @Operation(summary = "대외활동 수정", description = "기존 보유 대외활동 정보를 수정합니다")
    @PatchMapping("/{id}")
    fun updateActivity(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable id: Int,
        @RequestBody dto: ActivityUpdateDto
    ): Activity {
        return activityService.updateActivity(user.userId, id, dto)
    }

    @Operation(summary = "대외활동 삭제", description = "보유 대외활동을 삭제합니다")
    @DeleteMapping("/{id}")
    fun deleteActivity(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable id: Int
    ): ResponseEntity<Unit> {
        activityService.deleteActivity(user.userId, id)
        return ResponseEntity.ok().build()
    }

    @Operation(summary = "대외활동 순서 교체", description = "두 대외활동의 priority를 서로 교체합니다")
    @PostMapping("/swap-priority")
    fun swapActivityPriority(
        @AuthenticationPrincipal user: UserPrincipal,
        @RequestBody request: ActivityService.SwapPriorityRequest
    ): ResponseEntity<Unit> {
        activityService.swapActivityPriority(user.userId, request.activityId1, request.activityId2)
        return ResponseEntity.ok().build()
    }
}

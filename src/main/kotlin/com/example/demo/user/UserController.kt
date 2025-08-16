package com.example.demo.user
import com.example.demo.security.UserPrincipal
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@SecurityRequirement(name = "bearerAuth")
@RestController
@Tag(name = "user-controller", description = "유저 정보 API")
@RequestMapping("/user")
class UserController(private val userService: UserService) {

    @Operation(summary = "전체 사용자 조회", description = "관리자만 접계가능한 전체 사용자 목록 조회")
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    fun all() = userService.findAll()

    @Operation(summary = "내 정보 조회", description = "현재 로그인된 사용자의 정보를 조회합니다")
    @GetMapping("/")
    fun getMyInfo(@AuthenticationPrincipal user: UserPrincipal): User {
        return userService.getMyInfo(user.userId)
    }

//    @PostMapping("/{id}/update")
//    fun update(
//        @PathVariable id: Int,
//        @RequestBody dto: UserUpdateDto
//    ): ResponseEntity<UserResponse> {
//        val updated = userService.postStylePartialUpdate(id, dto)
//        return ResponseEntity.ok(UserResponse.from(updated))
//    }

    @Operation(summary = "내 정보 수정", description = "현재 로그인된 사용자의 정보를 수정합니다")
    @PatchMapping("/update")
    fun updateMe(
        @AuthenticationPrincipal user: UserPrincipal,
        @RequestBody dto: UserUpdateDto
    ): ResponseEntity<UserResponse> {
        val updated = userService.postStylePartialUpdate(user.userId, dto)
        return ResponseEntity.ok(UserResponse.from(updated))
    }

    @Operation(summary = "회원 탈퇴 (완전 삭제)", description = "현재 사용자를 데이터베이스에서 완전히 삭제합니다")
    @DeleteMapping("/delete")
    fun remove(@AuthenticationPrincipal user: UserPrincipal) {
        userService.delete(user.userId)
    }

    @Operation(summary = "회원 비활성화", description = "현재 사용자를 비활성화 상태로 변경합니다")
    @PostMapping("/soft-delete")
    fun softDelete(@AuthenticationPrincipal user: UserPrincipal): ResponseEntity<Unit> {
        userService.softDelete(user.userId)
        return ResponseEntity.ok().build()
    }
}

data class UserResponse(
    val id: Int,
    val name: String,
    val email: String?,
    val school: String?,
    val schoolStatus: String?,
    val major: String?,
    val grade: String?,
    val isGradePublic: Boolean,
    val residenceCity: String?,
    val residenceCountry: String?,
    val isResidencePublic: Boolean,
    val bio: String?,
    val vision: String?
) {
    companion object {
        fun from(u: User) = UserResponse(
            id = u.id,
            name = u.name,
            email = u.email,
            school = u.school,
            schoolStatus = u.schoolStatus,
            major = u.major,
            grade = u.grade,
            isGradePublic = u.isGradePublic,
            residenceCity = u.residenceCity,
            residenceCountry = u.residenceCountry,
            isResidencePublic = u.isResidencePublic,
            bio = u.bio,
            vision = u.vision
        )
    }
}

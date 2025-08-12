package com.example.demo.user
import com.example.demo.auth.security.UserPrincipal
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {


    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    fun all() = userService.findAll()


    @GetMapping("/me")
    fun getMyInfo(@AuthenticationPrincipal user: UserPrincipal): User {
        return userService.getMyInfo(user.userId)
    }

    @PostMapping("/{id}/update")
    fun update(
        @PathVariable id: Int,
        @RequestBody dto: UserUpdateDto
    ): ResponseEntity<UserResponse> {
        val updated = userService.postStylePartialUpdate(id, dto)
        return ResponseEntity.ok(UserResponse.from(updated))
    }

    @PostMapping("/update")
    fun updateMe(
        @AuthenticationPrincipal user: UserPrincipal,
        @RequestBody dto: UserUpdateDto
    ): ResponseEntity<UserResponse> {
        val updated = userService.postStylePartialUpdate(user.userId, dto)
        return ResponseEntity.ok(UserResponse.from(updated))
    }


}

data class UserResponse(
    val id: Int,
    val name: String,
    val email: String?,
    val school: String?,
    val schoolStatus: String?,
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

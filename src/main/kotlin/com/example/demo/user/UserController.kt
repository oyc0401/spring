package com.example.demo.user
import com.example.demo.auth.security.UserPrincipal
import io.swagger.v3.oas.annotations.security.SecurityRequirement
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

//    @PutMapping("/{id}")
//    fun edit(@PathVariable id: Int, @RequestBody u: User) = userService.update(id, u)

}

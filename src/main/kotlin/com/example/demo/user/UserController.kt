package com.example.demo.user

import com.example.demo.auth.getTokenFromHeader
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.web.bind.annotation.*

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @GetMapping("/all")
    fun all() = userService.findAll()


    @GetMapping("/me")
    fun getMyInfo(@RequestHeader("Authorization") authHeader: String?): User {
        val token = getTokenFromHeader(authHeader)

        return userService.getMyInfo(token)
    }

//    @PutMapping("/{id}")
//    fun edit(@PathVariable id: Int, @RequestBody u: User) = userService.update(id, u)

}

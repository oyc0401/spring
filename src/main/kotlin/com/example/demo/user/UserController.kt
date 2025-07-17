package com.example.demo.user

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @GetMapping
    fun all() = userService.findAll()


    @GetMapping("/me")
    fun getMyInfo(@RequestHeader("Authorization") authHeader: String): User {
        if (!authHeader.startsWith("Bearer ")) {
            throw IllegalArgumentException("Invalid Authorization header")
        }
        val token = authHeader.removePrefix("Bearer ").trim()

        return userService.getMyInfo(token)
    }

    @PutMapping("/{id}")
    fun edit(@PathVariable id: Int, @RequestBody u: User) = userService.update(id, u)

}

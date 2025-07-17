package com.example.demo.user

import com.example.demo.user.User
import com.example.demo.user.UserService
import com.example.demo.user.dto.SignupRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(private val service: UserService) {

   @GetMapping("/hello")
    fun hello(): String {
        return "Hello, Spring Boot!"
    }
    
    @GetMapping
    fun all() = service.findAll()

    @PostMapping
    fun add(@RequestBody user: SignupRequest) = service.create(user)

    @PutMapping("/{id}")
    fun edit(@PathVariable id: Int, @RequestBody u: User) = service.update(id, u)

    @DeleteMapping("/{id}")
    fun remove(@PathVariable id: Int) = service.delete(id)
}

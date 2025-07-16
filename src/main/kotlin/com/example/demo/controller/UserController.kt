package com.example.demo.controller

import com.example.demo.entity.User
import com.example.demo.service.UserService
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
    fun add(@RequestBody user: User) = service.create(user)

    @PutMapping("/{id}")
    fun edit(@PathVariable id: Int, @RequestBody u: User) = service.update(id, u)

    @DeleteMapping("/{id}")
    fun remove(@PathVariable id: Int) = service.delete(id)
}

package com.example.demo

import org.springframework.web.bind.annotation.GetMapping

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/healthz")
class HealthCheck {

    @GetMapping
    fun healthz(): String {

        return "안녕"
    }
}

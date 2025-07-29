package com.example.demo

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/log")
class LogController {
    private val logger = LoggerFactory.getLogger(LogController::class.java)

    @GetMapping
    fun testLog(): String {
        logger.info("✅ Loki 로그 테스트: ${LocalDateTime.now()}")
        return "로그 전송됨"
    }
}

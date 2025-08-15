package com.example.demo

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@Tag(name = "log-controller", description = "로그 테스트 API")
@RequestMapping("/log")
class LogController {
    private val logger = LoggerFactory.getLogger(LogController::class.java)

    @Operation(summary = "로그 테스트", description = "Loki 로그 시스템 연동 테스트를 수행합니다")
    @GetMapping
    fun testLog(): String {
        logger.info("✅ Loki 로그 테스트: ${LocalDateTime.now()}")
        return "로그 전송됨"
    }
}

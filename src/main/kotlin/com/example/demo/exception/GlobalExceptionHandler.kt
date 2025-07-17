package com.example.demo.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime
import javax.naming.AuthenticationException


@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgument(e: IllegalArgumentException): ResponseEntity<ErrorResponse> {
        return buildErrorResponse("BAD_REQUEST", e.message ?: "BAD_REQUEST", HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(AuthenticationException::class)
    fun handleAuthError(e: AuthenticationException): ResponseEntity<ErrorResponse> {
        return buildErrorResponse("UNAUTHORIZED", e.message ?: "UNAUTHORIZED", HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<ErrorResponse> {
        return buildErrorResponse("NOT_FOUND", e.message ?: "NOT_FOUND", HttpStatus.NOT_FOUND)
    }

    private fun buildErrorResponse(code: String, message: String?, status: HttpStatus): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(
                code = code,
                message = message ?: "오류가 발생했습니다."
            ),
            status
        )
    }

    data class ErrorResponse(
        val code: String,
        val message: String,
        val timestamp: String = LocalDateTime.now().toString()
    )
}

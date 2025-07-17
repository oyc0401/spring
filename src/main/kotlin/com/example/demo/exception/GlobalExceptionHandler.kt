//package com.example.demo.exception
//
//import org.springframework.http.HttpStatus
//import org.springframework.http.ResponseEntity
//import org.springframework.web.bind.annotation.ControllerAdvice
//import org.springframework.web.bind.annotation.ExceptionHandler
//import org.springframework.web.server.ResponseStatusException
//import java.time.LocalDateTime
//
//class InvalidTokenException : RuntimeException("유효하지 않은 토큰입니다.")
//class UserNotFoundException : RuntimeException("사용자를 찾을 수 없습니다.")
//class InvalidPasswordException : RuntimeException("비밀번호가 틀렸습니다.")
//class InvalidAuthorizationHeaderException : RuntimeException("Authorization 헤더가 잘못되었습니다.")
//
//
//@ControllerAdvice
//class GlobalExceptionHandler {
//
//    @ExceptionHandler(InvalidTokenException::class)
//    fun handleInvalidToken(e: InvalidTokenException): ResponseStatusException {
//        return ResponseStatusException(HttpStatus.UNAUTHORIZED, "INVALID_TOKEN")
////        return buildErrorResponse("INVALID_TOKEN", e.message, HttpStatus.UNAUTHORIZED)
//    }
//
//    @ExceptionHandler(InvalidPasswordException::class)
//    fun handleInvalidPassword(e: InvalidPasswordException): ResponseStatusException {
//        return ResponseStatusException(HttpStatus.UNAUTHORIZED, "INVALID_PASSWORD")
////        return buildErrorResponse("INVALID_PASSWORD", e.message, HttpStatus.UNAUTHORIZED)
//    }
//
//    @ExceptionHandler(UserNotFoundException::class)
//    fun handleUserNotFound(e: UserNotFoundException): ResponseStatusException {
//        return ResponseStatusException(HttpStatus.NOT_FOUND, "USER_NOT_FOUND")
//       // return buildErrorResponse("USER_NOT_FOUND", e.message, HttpStatus.NOT_FOUND)
//    }
//
//    @ExceptionHandler(InvalidAuthorizationHeaderException::class)
//    fun handleInvalidAuthHeader(e: InvalidAuthorizationHeaderException): ResponseStatusException {
//        return ResponseStatusException(HttpStatus.BAD_REQUEST, "INVALID_AUTH_HEADER")
//        //return buildErrorResponse("INVALID_AUTH_HEADER", e.message, HttpStatus.BAD_REQUEST)
//    }
//
//    @ExceptionHandler(IllegalArgumentException::class)
//    fun handleIllegalArgument(e: IllegalArgumentException): ResponseStatusException {
//        return ResponseStatusException(HttpStatus.BAD_REQUEST, "INVALID_PASSWORD")
//       // return buildErrorResponse("BAD_REQUEST", e.message ?: "잘못된 요청입니다.", HttpStatus.BAD_REQUEST)
//    }
//
////    @ExceptionHandler(Exception::class)
////    fun handleAll(e: Exception, request: HttpServletRequest): ResponseEntity<ErrorResponse>? {
////        // Swagger 요청 예외는 처리하지 않음 (swagger-ui, v3/api-docs 등)
////        val path = request.requestURI
////        if (path.startsWith("/v3/api-docs") || path.startsWith("/swagger")) {
////            return null
////        }
////
////        return buildErrorResponse("INTERNAL_SERVER_ERROR", e.message ?: "알 수 없는 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR)
////    }
//
//    private fun buildErrorResponse(code: String, message: String?, status: HttpStatus): ResponseEntity<ErrorResponse> {
//        return ResponseEntity(
//            ErrorResponse(
//                code = code,
//                message = message ?: "오류가 발생했습니다."
//            ),
//            status
//        )
//    }
//
//    data class ErrorResponse(
//        val code: String,
//        val message: String,
//        val timestamp: String = LocalDateTime.now().toString()
//    )
//}

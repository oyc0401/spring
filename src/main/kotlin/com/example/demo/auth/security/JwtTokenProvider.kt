package com.example.demo.auth.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.util.Date
import java.util.concurrent.ConcurrentHashMap

@Component
class JwtTokenProvider {
    private val secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256)

    private val accessTokenValidityInMs = 60 * 60 * 1000L * 1 // 1시간
    private val refreshTokenValidityInMs = 7 * 24 * 60 * 60 * 1000L // 7일

    // 메모리 기반 리프레시 토큰 저장소
    private val refreshTokenStore: MutableMap<Int, String> = ConcurrentHashMap()

    /** 액세스 토큰 생성 */
    fun generateAccessToken(userId: Int): String {
        val now = Date()
        val expiryDate = Date(now.time + accessTokenValidityInMs)

        val role = if (userId == 5) "ROLE_ADMIN" else "ROLE_USER"

        return Jwts.builder()
            .setSubject(userId.toString())
            .claim("role", role)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(secretKey)
            .compact()
    }

    /** 리프레시 토큰 생성 및 저장 */
    fun generateRefreshToken(userId: Int): String {
        val now = Date()
        val expiryDate = Date(now.time + refreshTokenValidityInMs)

        val refreshToken = Jwts.builder()
            .setSubject(userId.toString())
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(secretKey)
            .compact()

        // 메모리 저장
        refreshTokenStore[userId] = refreshToken
        return refreshToken
    }

    /** 액세스 토큰 유효성 검증 */
    fun validateToken(token: String): Boolean {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
            true
        } catch (e: Exception) {
            false
        }
    }

    /** 리프레시 토큰 유효성 검증 + 저장소와 비교 */
    fun validateRefreshToken(token: String): Boolean {
        return try {
            val claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .body
            val userId = claims.subject.toInt()
            val storedToken = refreshTokenStore[userId]
            storedToken == token
        } catch (e: Exception) {
            false
        }
    }

    /** 액세스 토큰에서 userId 추출 */
    fun getUserIdFromToken(token: String): Int {
        val claims = Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .body
        return claims.subject.toInt()
    }

    fun getRoleFromToken(token: String): String {
        val claims = Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .body
        return claims["role"] as? String ?: "ROLE_USER"
    }

    /** 리프레시 토큰에서 userId 추출 */
    fun getUserIdFromRefreshToken(token: String): Int {
        val claims = Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .body
        return claims.subject.toInt()
    }

    /** 리프레시 토큰 삭제 (예: 로그아웃 시) */
    fun removeRefreshToken(userId: Int) {
        refreshTokenStore.remove(userId)
    }
}
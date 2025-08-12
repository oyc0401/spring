package com.example.demo.auth

import com.example.demo.auth.security.JwtTokenProvider
import com.example.demo.user.User
import com.example.demo.user.UserRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import javax.naming.AuthenticationException
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository,
    private val jwtTokenProvider: JwtTokenProvider,
    @Qualifier("googleJwtDecoder") private val google: JwtDecoder,
    @Qualifier("appleJwtDecoder") private val apple: JwtDecoder,
) {

    data class SignupRequest(
        val username: String,
        val password: String,
        val name: String,
    )

    data class SignupProviderRequest(
        val idToken: String,
        val name: String,
    )

    data class LoginRequest(
        val username: String,
        val password: String
    )

    data class LoginProviderRequest(
        val idToken: String,
    )

    data class LoginResponse(
        val accessToken: String,
        val refreshToken: String
    )

    fun registerByCredentials(request: SignupRequest): User {

        // 1. 유저네임 중복 검사
        if (authRepository.existsByUsername(request.username)) {
            throw IllegalArgumentException("이미 사용 중인 유저네임입니다.")
        }

        val user = userRepository.save(
            User(
                name = request.name
            )
        )
        System.out.println(user.id)

        val auth = Auth(
            user = user,
            username = request.username,
            password = request.password,
            loginProvider = "credentials",
            role = "ROLE_USER"
        )

        authRepository.save(auth)

        return user
    }

    @Transactional
    fun registerByGoogle(req: SignupProviderRequest): User =
        registerCommon(
            decoder = google,
            provider = "google",
            idToken = req.idToken,
            fallbackName = req.name
        )

    @Transactional
    fun registerByApple(req: SignupProviderRequest): User =
        registerCommon(
            decoder = apple,
            provider = "apple", // ✅ 버그 수정
            idToken = req.idToken,
            fallbackName = req.name
        )

    // 유저 반환되면 클라에서 로그인 api또 호출하게...? 아니면 여기서 아예 다 로그인처리..?
    private fun registerCommon(
        decoder: JwtDecoder,
        provider: String,
        idToken: String,
        fallbackName: String
    ): User {
//        val jwt = try {
//            decoder.decode(idToken)
//        } catch (ex: Exception) {
//            throw AuthenticationException("Invalid token:" + ex)
//        }
        val uid = idToken // jwt.subject                       // 고유 UID(sub)
//        val email = jwt.getClaim<String>("email")   // 애플은 이후 null일 수 있음
//        val nameFromToken = jwt.getClaim<String>("name")


        // 1) 이미 가입된 UID면 기존 유저 반환
        authRepository.findByLoginProviderAndOauthId(provider, uid)?.let { existing ->
            return userRepository.findById(existing.userId).orElseThrow()
        }

        // 2) 신규 유저 생성
        val user = userRepository.save(
            User(
                name = fallbackName,
//                email = email // nullable 허용 시
            )
        )

        authRepository.save(
            Auth(
                user = user, // 같은 트랜잭션이라 이렇게 전달해야함.
                loginProvider = provider,
                oauthId = uid,
                role = "ROLE_USER",
            )
        )

        return user
    }

    fun login(request: LoginRequest): LoginResponse {
        val auth = authRepository.findByUsername(request.username)
            ?: throw NoSuchElementException("User not found")

        if (auth.password != request.password) {
            throw AuthenticationException("Invalid password")
        }

        val accessToken = jwtTokenProvider.generateAccessToken(auth.userId, auth.role)
        val refreshToken = jwtTokenProvider.generateRefreshToken(auth.userId)

        return LoginResponse(accessToken, refreshToken)
    }

    @Transactional(readOnly = true)
    fun loginGoogle(request: LoginProviderRequest): LoginResponse {
        // 1) ID 토큰 검증 + 디코딩 (서명/iss/aud/exp 자동 검증)
//        val jwt = try {
//            google.decode(request.idToken)
//        } catch (ex: Exception) {
//            throw AuthenticationException("Invalid Google ID token:" + ex)
//        }

        // 2) UID(sub) 추출
        val uid = request.idToken //jwt.subject
        val auth = authRepository.findByLoginProviderAndOauthId("google", uid)
            ?: throw NoSuchElementException("User not found")

        // 4) 우리 서비스용 토큰 발급
        val accessToken = jwtTokenProvider.generateAccessToken(auth.userId, auth.role)
        val refreshToken = jwtTokenProvider.generateRefreshToken(auth.userId)

        return LoginResponse(accessToken, refreshToken)
    }


    fun reissueAccessToken(refreshToken: String): String {
        if (!jwtTokenProvider.validateRefreshToken(refreshToken)) {
            throw AuthenticationException("Invalid or expired refresh token")
        }

        val userId = jwtTokenProvider.getUserIdFromRefreshToken(refreshToken)

        val auth = authRepository.findById(userId)
            .orElseThrow { NoSuchElementException("User not found") }

        return jwtTokenProvider.generateAccessToken(userId, auth.role)
    }

    fun logout(userId: Int) {
        jwtTokenProvider.removeRefreshToken(userId)
    }

    fun delete(userId: Int) {
        return userRepository.deleteById(userId) // auth는 cascade로 delete됌.
    }


}

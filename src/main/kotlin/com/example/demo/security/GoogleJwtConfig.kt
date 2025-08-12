package com.example.demo.security

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.core.*
import org.springframework.security.oauth2.jwt.*

@Configuration
class OidcJwtConfig(
    @Value("\${google.client-id}") private val googleClientId: String,
    @Value("\${apple.client-id}")  private val appleClientId: String, // iOS는 번들ID, Web은 Service ID
) {
    @Bean
    @Qualifier("googleJwtDecoder")
    fun googleJwtDecoder(): JwtDecoder {
        val decoder = JwtDecoders.fromIssuerLocation("https://accounts.google.com") as NimbusJwtDecoder
        val withIssuer = JwtValidators.createDefaultWithIssuer("https://accounts.google.com")
        val audCheck = OAuth2TokenValidator<Jwt> { jwt ->
            if (jwt.audience.contains(googleClientId)) OAuth2TokenValidatorResult.success()
            else OAuth2TokenValidatorResult.failure(OAuth2Error("invalid_token", "aud mismatch for Google", null))
        }
        decoder.setJwtValidator(DelegatingOAuth2TokenValidator(withIssuer, audCheck))
        return decoder
    }

    @Bean
    @Qualifier("appleJwtDecoder")
    fun appleJwtDecoder(): JwtDecoder {
        // 애플 OIDC 이슈어 (JWK 자동 디스커버리 지원)
        val decoder = JwtDecoders.fromIssuerLocation("https://appleid.apple.com") as NimbusJwtDecoder
        val withIssuer = JwtValidators.createDefaultWithIssuer("https://appleid.apple.com")
        val audCheck = OAuth2TokenValidator<Jwt> { jwt ->
            if (jwt.audience.contains(appleClientId)) OAuth2TokenValidatorResult.success()
            else OAuth2TokenValidatorResult.failure(OAuth2Error("invalid_token", "aud mismatch for Apple", null))
        }
        decoder.setJwtValidator(DelegatingOAuth2TokenValidator(withIssuer, audCheck))
        return decoder
    }
}
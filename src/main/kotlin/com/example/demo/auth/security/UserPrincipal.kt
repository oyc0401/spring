package com.example.demo.auth.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class UserPrincipal(
    val userId: Int,
    val role: String
) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority> =
        listOf(SimpleGrantedAuthority(role))

    override fun getUsername(): String = userId.toString()

    override fun getPassword(): String? = null
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
    override fun isCredentialsNonExpired() = true
    override fun isEnabled() = true

    companion object {
        fun fromToken(userId: Int, role: String): UserPrincipal {
            return UserPrincipal(userId, role)
        }
    }
}

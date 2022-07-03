package com.example.springbootsecurityjwt.security

import com.example.springbootsecurityjwt.entity.JwtUser
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class JwtAuthenticationToken(
    private val principal: JwtUser,
    private val credentials: String,
    authorities: MutableCollection<out GrantedAuthority>? = null
) : AbstractAuthenticationToken(authorities) {
    override fun getCredentials(): Any {
        return credentials
    }

    override fun getPrincipal(): Any {
        return principal
    }
}
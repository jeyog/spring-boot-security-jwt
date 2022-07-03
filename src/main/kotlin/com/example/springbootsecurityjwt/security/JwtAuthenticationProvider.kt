package com.example.springbootsecurityjwt.security

import com.example.springbootsecurityjwt.service.JwtService
import io.jsonwebtoken.JwtException
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.stereotype.Component
import org.springframework.util.Assert

@Component
class JwtAuthenticationProvider(
    private val jwtService: JwtService
): AuthenticationProvider {
    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication?): Authentication {
        Assert.notNull(authentication, "authentication cannot be null")
        val token = authentication!!.credentials as String
        try {
            if (jwtService.validateToken(token)) {
                val jwtAuthenticationToken = JwtAuthenticationToken(jwtService.getJwtUser(token), token, arrayListOf())
                jwtAuthenticationToken.isAuthenticated = true
                return jwtAuthenticationToken
            }
        } catch (e: Exception) {
            when (e) {
                is IllegalArgumentException, is JwtException -> {
                    throw BadCredentialsException("Invalid token")
                }
            }
        }
        throw BadCredentialsException("Invalid token")
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return authentication?.let { JwtAuthenticationToken::class.java.isAssignableFrom(it) } ?: false
    }
}
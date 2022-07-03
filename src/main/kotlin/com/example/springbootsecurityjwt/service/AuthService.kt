package com.example.springbootsecurityjwt.service

import com.example.springbootsecurityjwt.dto.JwtUserDTO
import com.example.springbootsecurityjwt.dto.TokenDTO
import com.example.springbootsecurityjwt.entity.JwtUser
import com.example.springbootsecurityjwt.properties.JwtProperties
import com.example.springbootsecurityjwt.security.JwtAuthenticationToken
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val jwtProperties: JwtProperties,
    private val authenticationManager: AuthenticationManager,
    private val jwtService: JwtService
) {
    fun token(request: JwtUserDTO): TokenDTO {
        val jwtUser = JwtUser(request)
        val accessToken = jwtService.generateToken(jwtUser)
        val expiresIn = jwtProperties.expiration
        authenticationManager.authenticate(JwtAuthenticationToken(jwtUser, accessToken, arrayListOf()))
        return TokenDTO(accessToken = accessToken, expiresIn = expiresIn)
    }
}
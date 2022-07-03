package com.example.springbootsecurityjwt.controller

import com.example.springbootsecurityjwt.dto.JwtUserDTO
import com.example.springbootsecurityjwt.dto.TokenDTO
import com.example.springbootsecurityjwt.service.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {
    @PostMapping("/token")
    fun token(@RequestBody request: JwtUserDTO): ResponseEntity<TokenDTO> {
        val response = authService.token(request)
        return ResponseEntity.ok(response)
    }
}
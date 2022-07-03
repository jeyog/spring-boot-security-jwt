package com.example.springbootsecurityjwt.controller

import com.example.springbootsecurityjwt.dto.JwtUserDTO
import com.example.springbootsecurityjwt.entity.JwtUser
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {
    @GetMapping("/hello")
    fun hello(@AuthenticationPrincipal user: JwtUser): ResponseEntity<JwtUserDTO> {
        return ResponseEntity.ok(JwtUserDTO(user))
    }
}
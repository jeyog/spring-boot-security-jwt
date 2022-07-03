package com.example.springbootsecurityjwt.controller

import com.example.springbootsecurityjwt.dto.ErrorDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/error")
class ErrorController {
    @GetMapping("/unauthorized")
    fun unauthorized(): ResponseEntity<ErrorDTO> {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorDTO(
            status = HttpStatus.UNAUTHORIZED,
            message = "인가되지 않은 사용자입니다."
        ))
    }
}
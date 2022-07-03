package com.example.springbootsecurityjwt.dto

import org.springframework.http.HttpStatus
import java.time.LocalDateTime
import java.util.stream.Collectors

data class ErrorDTO(
    val timestamp: LocalDateTime,
    val status: Int,
    val error: String,
    val path: String,
    val message: Any,
) {
    constructor(status: HttpStatus, message: Any) : this(
        timestamp = LocalDateTime.now(),
        status = status.value(),
        error = status.name
            .split('_')
            .stream()
            .map{
                s -> s[0] + s.substring(1).lowercase()
            }
            .collect(Collectors.toList())
            .joinToString(separator = " "),
        path = "/",
        message = message
    )

}
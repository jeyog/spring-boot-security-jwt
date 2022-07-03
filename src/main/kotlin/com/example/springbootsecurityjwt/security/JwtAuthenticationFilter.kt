package com.example.springbootsecurityjwt.security

import com.example.springbootsecurityjwt.entity.JwtUser
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Configuration
class JwtAuthenticationFilter: OncePerRequestFilter() {
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        if (Regex("/error/.*").matches(request.requestURI)) {
            return filterChain.doFilter(request, response)
        }
        val token = resolveToken(request) ?: return filterChain.doFilter(request, response)
        val jwtAuthenticationToken = JwtAuthenticationToken(JwtUser("", ""), token, arrayListOf())
        SecurityContextHolder.getContext().authentication = jwtAuthenticationToken

        filterChain.doFilter(request, response)
    }

    fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION)
        return if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring(7)
        } else {
            null
        }
    }

}
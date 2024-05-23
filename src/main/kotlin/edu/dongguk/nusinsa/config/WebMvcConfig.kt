package edu.dongguk.nusinsa.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * Cors 처리
 * 모든 URL에 대해
 * 3000, 3002번 포트 접근 허용
 * 모든 HTTP 메서드 허용
 * 모든 헤더 허용
 */
@Configuration
class WebMvcConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins(
                "http://localhost:3000",
                "https://localhost:3000",
                "http://localhost:3002",
                "https://localhost:3002"
            )
            .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
            .allowedHeaders("*")
            .exposedHeaders("*")
        super.addCorsMappings(registry)
    }
}
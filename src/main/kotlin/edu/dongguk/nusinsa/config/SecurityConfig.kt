package edu.dongguk.nusinsa.config

import edu.dongguk.nusinsa.security.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val customUserDetailService: CustomUserDetailService,
    private val jwtAccessDeniedHandler: JwtAccessDeniedHandler,
    private val jwtProvider: JwtProvider,
    private val jwtEntryPoint: JwtEntryPoint
) {

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf(
            "http://localhost:3000",
            "https://localhost:3000",
            "http://localhost:3001",
            "https://localhost:3001",
            "http://localhost:3002",
            "https://localhost:3002",)
        configuration.allowedMethods = listOf(
            "GET",
            "POST",
            "PUT",
            "PATCH",
            "DELETE")
        configuration.allowedHeaders = listOf("*")
        configuration.exposedHeaders = listOf("*")

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)

        return source
    }

    @Bean
    @Throws(Exception::class)
    protected fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            httpBasic { disable() }
            cors { corsConfigurationSource() }
            csrf { disable() }
            sessionManagement { sessionCreationPolicy = SessionCreationPolicy.STATELESS }
            formLogin { disable() }
            authorizeHttpRequests {
                authorize("/api/v1/customers/**", hasRole("CUSTOMER"))
                authorize("/api/v1/auth/**", permitAll)
                authorize(anyRequest, authenticated)
            }
            exceptionHandling {
                authenticationEntryPoint = jwtEntryPoint
                accessDeniedHandler = jwtAccessDeniedHandler
            }
            addFilterBefore<UsernamePasswordAuthenticationFilter>(JwtAuthenticationFilter(jwtProvider, customUserDetailService))
            addFilterBefore<JwtAuthenticationFilter>(JwtExceptionFilter())
        }
        return http.build()
    }

}
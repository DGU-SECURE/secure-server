package edu.dongguk.nusinsa.security

import edu.dongguk.nusinsa.exception.ErrorCode
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.UnsupportedJwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.filter.OncePerRequestFilter

class JwtExceptionFilter : OncePerRequestFilter() {
    private val urls = listOf(
        "/favicon.ico",
        "/api/v1/auth/join", "/api/v1/auth/login"
    )

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        response.characterEncoding = "utf-8"

        var isException = false
        try {
            filterChain.doFilter(request, response)
        } catch (e: SecurityException) {
            request.setAttribute("exception", ErrorCode.ACCESS_DENIED_ERROR)
            isException = true
        } catch (e: MalformedJwtException) {
            request.setAttribute("exception", ErrorCode.TOKEN_MALFORMED_ERROR)
            isException = true
        } catch (e: IllegalArgumentException) {
            request.setAttribute("exception", ErrorCode.TOKEN_TYPE_ERROR)
            isException = true
        } catch (e: ExpiredJwtException) {
            request.setAttribute("exception", ErrorCode.TOKEN_EXPIRED_ERROR)
            isException = true
        } catch (e: UnsupportedJwtException) {
            request.setAttribute("exception", ErrorCode.TOKEN_UNSUPPORTED_ERROR)
            isException = true
        } catch (e: JwtException) {
            request.setAttribute("exception", ErrorCode.TOKEN_UNKNOWN_ERROR)
            isException = true
        } catch (e: Exception) {
            request.setAttribute("exception", ErrorCode.NOT_FOUND_ERROR)
            isException = true
        }

        if (isException) {
            filterChain.doFilter(request, response)
        }
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        return request.requestURI in urls
    }
}

package edu.dongguk.nusinsa.controller

import edu.dongguk.nusinsa.dto.ResponseDto
import edu.dongguk.nusinsa.dto.request.JoinDto
import edu.dongguk.nusinsa.dto.request.LoginDto
import edu.dongguk.nusinsa.dto.response.LoginResultDto
import edu.dongguk.nusinsa.service.AuthService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth/")
class AuthController(
    private val authService: AuthService
) {
    @PostMapping("/join")
    fun joinHeadquarters(@RequestBody @Valid joinDto: JoinDto): ResponseDto<Any> {
        return ResponseDto.success(authService.join(joinDto))
    }

    @PostMapping("/login")
    fun loginHeadquarters(@RequestBody loginDto: LoginDto): ResponseDto<LoginResultDto> {
        return ResponseDto.success(authService.login(loginDto))
    }
}
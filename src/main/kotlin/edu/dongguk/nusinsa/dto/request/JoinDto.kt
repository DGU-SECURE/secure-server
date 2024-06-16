package edu.dongguk.nusinsa.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

class JoinDto(
    @JsonProperty("login_id")
    @field:Pattern(
        regexp = "^[A-Za-z0-9]{6,15}\$",
        message = "아이디는 6글자 이상 15글자 이하, 영어, 숫자 포함입니다."
    )
    val loginId: String,
    @field:Pattern(
        regexp = "^(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@\$%^&*-]).{8,15}\$",
        message = "비밀번호 8글자 이상 15글자 이하, 영어, 숫자, 특수문자 포함입니다."
    )
    val password: String,
    @field:NotBlank(message = "이름를 입력해주세요")
    val name: String,
    @field:NotBlank(message = "전화번호를 입력해주세요")
    val address: String
) {
}
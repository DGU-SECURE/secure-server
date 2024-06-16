package edu.dongguk.nusinsa.dto.request

import com.fasterxml.jackson.annotation.JsonProperty

class LoginDto(
    @JsonProperty("login_id")
    val loginId: String,
    val password: String
) {
}
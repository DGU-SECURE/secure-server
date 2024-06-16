package edu.dongguk.nusinsa.dto.response

class LoginResultDto(
    val name: String,
    val access_token: String,
    val refresh_token: String,
    val address: String
) {
}
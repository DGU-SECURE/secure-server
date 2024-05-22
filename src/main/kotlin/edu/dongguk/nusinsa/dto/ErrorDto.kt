package edu.dongguk.nusinsa.dto

import edu.dongguk.nusinsa.exception.ErrorCode

/**
 * 커스텀 예외나 모든 예외는 해당 Dto로 변경 후 ResponseDto에 저장
 */
data class ErrorDto(
    private val error: ErrorCode
) {
    val code = error.getCode()
    val message = error.getMessage()
}
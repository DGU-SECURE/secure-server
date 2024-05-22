package edu.dongguk.nusinsa.exception

import edu.dongguk.nusinsa.dto.ErrorDto
import edu.dongguk.nusinsa.dto.ResponseDto
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * 에러 처리
 */
@RestControllerAdvice
class ExceptionHandler {
    /**
     * 공통 에러 처리
     */
    @ExceptionHandler(GlobalException::class)
    fun globalException(e: GlobalException) = ResponseDto.fail(ErrorDto(e.getErrorCode()))
}